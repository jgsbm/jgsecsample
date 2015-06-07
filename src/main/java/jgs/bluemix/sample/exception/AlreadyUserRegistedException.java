package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.MessageCodeEnum;

/**
 * 既にユーザが登録済である事を表す業務例外です.
 *
 * @author ryozo
 */
public class AlreadyUserRegistedException extends BusinessException {
    private static final long serialVersionUID = -6588631994167653882L;

    public AlreadyUserRegistedException() {
        this(null);
    }

    public AlreadyUserRegistedException(Throwable cause) {
        super(MessageCodeEnum.ERROR_ALREADY_USER_REGISTED, cause);
    }
}
