package jgs.bluemix.sample.message;

import lombok.Getter;

/**
 * 業務系メッセージのメッセージコードを保持するEnumです.
 *
 * @author ryozo
 */
@Getter
public enum BusinessMessageCodeEnum {
    // INFOメッセージ
    INFO_ORDER_SUCCESS           ("info.business.orderSuccess"),
    INFO_UPDATE_CUSTOMER_SUCCESS ("info.business.updateCustomerSuccess"),
    // ERRORメッセージ
    ERROR_ALREADY_USER_REGISTED  ("error.business.alreadyUserRegisted"),
    ERROR_OUTOFSTOCK             ("error.business.outOfStock"),
    ERROR_UPDATE_USER 		 ("error.business.updateUser"),
    ERROR_FAILED_PASSWORD 	 ("error.business.failedPassword");

    private String code;

    BusinessMessageCodeEnum(String code) {
        this.code = code;
    }
}
