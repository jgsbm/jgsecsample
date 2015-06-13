package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.BusinessMessageCodeEnum;

/**
 * 商品が存在しないことによって何らかの業務操作が実施できない場合に発生する復帰不能例外です.
 *
 * @author ryozo
 */
public class ProductNotFoundException extends SystemException {

    private static final long serialVersionUID = -2967719464584428634L;

    public ProductNotFoundException() {
        this(null);
    }

    public ProductNotFoundException(Throwable cause) {
        super(BusinessMessageCodeEnum.ERROR_OUTOFSTOCK, cause);
    }
}
