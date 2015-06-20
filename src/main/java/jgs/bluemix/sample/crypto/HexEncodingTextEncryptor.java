package jgs.bluemix.sample.crypto;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 * 文字列に対する暗号化/複合化機能を提供します.
 * 実際はコンストラクタで指定された{@link BytesEncryptor}に処理を委譲します.
 *
 * なお、当クラスでは{@link Hex}や{@link Utf8}を利用していますが、
 * これは本来SpringSecurity内部で利用されるものであり、SpringSecurity外から利用すべきではありません.
 * 実際に実装する場合は、これらに相当するクラスを独自に作り込むこを推奨します.
 */
public class HexEncodingTextEncryptor implements TextEncryptor {

    private final BytesEncryptor encryptor;

    /**
     * Encryptorを設定してインスタンスを作成します.
     * @param encryptor
     */
    public HexEncodingTextEncryptor(BytesEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    /**
     * 暗号化します.
     * 暗号化後の文字列は16進エンコードされます.
     * @param text
     * @return
     */
    public String encrypt(String text) {
        return new String(Hex.encode(encryptor.encrypt(Utf8.encode(text))));
    }

    /**
     * 復号化します.
     * 当クラスの{@link #encrypt(String)}で暗号化された文字列のみを復号化できます.
     * @param encryptedText
     * @return
     */
    public String decrypt(String encryptedText) {
        return Utf8.decode(encryptor.decrypt(Hex.decode(encryptedText)));
    }

}

