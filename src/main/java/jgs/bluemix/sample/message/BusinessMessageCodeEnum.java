package jgs.bluemix.sample.message;

import lombok.Getter;

/**
 * 業務系メッセージのメッセージコードを保持するEnumです.
 *
 * @author ryozo
 */
@Getter
public enum BusinessMessageCodeEnum {
    // ERRORメッセージ
    ERROR_ALREADY_USER_REGISTED  ("error.business.alreadyUserRegisted"),
    ERROR_OUTOFSTOCK             ("error.business.outOfStock");

    private String code;

    BusinessMessageCodeEnum(String code) {
        this.code = code;
    }
}
