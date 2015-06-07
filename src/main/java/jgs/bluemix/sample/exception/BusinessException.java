package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.MessageCodeEnum;
import lombok.Getter;

/**
 * ユーザ操作によって復帰可能な業務例外を表すExceptionクラスです.
 * 業務で発生し得る各種例外は必要に応じて当Exceptionを継承して作成してください.
 * 当クラスはServiceレイヤで当該業務エラーを表す{@link MessageCodeEnum}が設定された上でthrowされます.
 *
 * 業務例外のため、例外処理はControllerでのTry-Catchか
 * Controller内の{@link org.springframework.web.bind.annotation.ExceptionHandler}で実施することを推奨します.
 *
 * @author ryozo
 */
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -5354511023020671632L;

    private MessageCodeEnum messageCode;

    public BusinessException(MessageCodeEnum messageCode) {
        this(messageCode, null);
    }

    public BusinessException(MessageCodeEnum messageCode, Throwable cause) {
        super(cause);
        this.messageCode = messageCode;
    }
}
