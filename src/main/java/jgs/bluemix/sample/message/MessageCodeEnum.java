package jgs.bluemix.sample.message;

import lombok.Getter;

/**
 * メッセージコードを保持するEnumです.
 *
 * @author ryozo
 */
@Getter
public enum MessageCodeEnum {
    // ERRORメッセージ
    ERROR_ALREADY_USER_REGISTED("error.business.alreadyUserRegisted");

    private String code;

    MessageCodeEnum(String code) {
        this.code = code;
    }
}
