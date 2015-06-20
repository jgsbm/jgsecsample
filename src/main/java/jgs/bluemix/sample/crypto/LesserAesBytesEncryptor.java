package jgs.bluemix.sample.crypto;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.GeneralSecurityException;

/**
 * 128bit鍵長のAES暗号化/復号化機能を提供します.
 * AES鍵長は256bitを利用することが望ましいですがJCEポリシーファイルの変更が必要となるため、
 * 当サンプルでは簡単のために128bit鍵長による暗号化を実施します.
 *
 * 本来であれは{@link org.springframework.security.crypto.encrypt.Encryptors}を利用してEncryptorを取得するべきです.
 * また、当クラスでは{@link Hex}を利用していますが、
 * これは本来SpringSecurity内部で利用されるものであり、SpringSecurity外から利用すべきではありません.
 * 実際に実装する場合は、これらに相当するクラスを独自に作り込むこを推奨します.
 *
 * @author ryozo
 */
public class LesserAesBytesEncryptor implements BytesEncryptor {

    private static final int IV_SIZE = 16;

    private final SecretKey secretKey;

    private final Cipher encryptor;

    private final Cipher decryptor;

    private final BytesKeyGenerator ivGenerator;

    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 暗号化パスワードとsaltを利用してEncryptorを生成します.
     * なお、saltは16進数文字(0...f)にのみ利用可能です.
     * @param password
     * @param salt
     */
    public LesserAesBytesEncryptor(String password, CharSequence salt) {
        try {
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), Hex.decode(salt), 1024, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey secretKey = factory.generateSecret(keySpec);
            this.secretKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
            encryptor = Cipher.getInstance(AES_ALGORITHM);
            decryptor = Cipher.getInstance(AES_ALGORITHM);
            this.ivGenerator = KeyGenerators.secureRandom(IV_SIZE);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 引数のByte配列を暗号化します.
     * @param bytes 暗号化対象Byte配列
     * @return 暗号化後のByte配列
     */
    public byte[] encrypt(byte[] bytes) {
        synchronized (encryptor) {
            byte[] iv = ivGenerator.generateKey();
            try {
                encryptor.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
                byte[] encBytes = encryptor.doFinal(bytes);

                // 復号化時に暗号化の際に利用したivを利用する必要があるため、暗号化時に利用したivを暗号化Bytesの先頭に差し込む。
                byte[] result = new byte[IV_SIZE + encBytes.length];
                System.arraycopy(iv,       0, result, 0,       IV_SIZE);        // ivの書き込み
                System.arraycopy(encBytes, 0, result, IV_SIZE, encBytes.length);  // ivの後ろに暗号化Bytes書き込み
                return result;
            } catch (GeneralSecurityException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * 引数の暗号化済Byte配列を復号します.
     * 当メソッドは当クラスの{@link #encrypt(byte[])}メソッドを利用して暗号化された情報のみ復号可能です.
     *
     * @param encryptedBytes 復号対象のByte配列
     * @return 復号化後のByte配列
     */
    public byte[] decrypt(byte[] encryptedBytes) {
        synchronized (decryptor) {
            // 暗号化文字列の中からivを取り出す
            byte[] src = new byte[encryptedBytes.length - IV_SIZE];
            byte[] iv = new byte[IV_SIZE];
            System.arraycopy(encryptedBytes, 0,       iv,  0, IV_SIZE);
            System.arraycopy(encryptedBytes, IV_SIZE, src, 0, encryptedBytes.length - IV_SIZE);
            try {
                decryptor.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
                return decryptor.doFinal(src);
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
