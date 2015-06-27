package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.BusinessMessageCodeEnum;

/**
 * ユーザ更新に失敗したことを表す業務例外です。
 *
 * @author takeshimikami
 */
public class UpdateUserException extends BusinessException {

    private static final long serialVersionUID = 3886787091388385085L;

    public UpdateUserException() {
        this(null);
    }

    public UpdateUserException(Throwable cause) {
        super(BusinessMessageCodeEnum.ERROR_UPDATE_USER, cause);
    }
}
