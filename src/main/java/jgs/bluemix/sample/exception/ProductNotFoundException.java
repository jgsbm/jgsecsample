package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.MessageCodeEnum;

/**
 * 商品が存在しないことによって何らかの業務操作が実施できない場合に発生する業務例外です.
 *
 * @author ryozo
 */
public class ProductNotFoundException extends BusinessException {

    private static final long serialVersionUID = -2967719464584428634L;

    public ProductNotFoundException() {
        this(null);
    }

    public ProductNotFoundException(Throwable cause) {
        super(MessageCodeEnum.ERROR_OUTOFSTOCK, cause);
    }
}
