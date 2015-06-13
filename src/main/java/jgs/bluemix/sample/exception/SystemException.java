package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.MessageCodeEnum;

/**
 * ユーザ操作によって復帰不可能な業務例外を表すExceptionクラスです.
 * 業務で発生し得る各種例外は必要に応じて当Exceptionを継承して作成してください.
 * 当Exception、または派生クラスがthrowされた場合、共通エラー画面に遷移します.
 *
 * @author ryozo
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = -6389577463585579568L;
    private MessageCodeEnum messageCode;

    public SystemException(MessageCodeEnum messageCode) {
        this(messageCode, null);
    }

    public SystemException(MessageCodeEnum messageCode, Throwable cause) {
        super(cause);
        this.messageCode = messageCode;
    }
}
