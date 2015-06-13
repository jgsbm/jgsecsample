package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.SysErrorMessageCodeEnum;
import lombok.Getter;

/**
 * ユーザ操作によって復帰不可能な業務例外を表すExceptionクラスです.
 * 業務で発生し得る各種例外は必要に応じて当Exceptionを継承して作成してください.
 * 当Exception、または派生クラスがthrowされた場合、共通エラー画面に遷移します.
 *
 * @author ryozo
 */
@Getter
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -4311304804948825917L;
    
    private SysErrorMessageCodeEnum errorCode;

    public SystemException(SysErrorMessageCodeEnum errorCode) {
        this(errorCode, null);
    }

    public SystemException(SysErrorMessageCodeEnum errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
}
