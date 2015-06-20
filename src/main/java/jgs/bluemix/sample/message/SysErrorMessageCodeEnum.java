package jgs.bluemix.sample.message;

import lombok.Getter;

/**
 * システム例外のメッセージコードを取り扱うEnumです.
 *
 * @author ryozo
 */
@Getter
public enum SysErrorMessageCodeEnum {
    ERROR_PRODUCT_NOTEXIST       ("error.system.productNotExist");

    private String code;

    SysErrorMessageCodeEnum(String code) {
        this.code = code;
    }
}
