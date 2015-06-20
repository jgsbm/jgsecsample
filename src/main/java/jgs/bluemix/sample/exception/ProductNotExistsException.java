package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.SysErrorMessageCodeEnum;

/**
 * 商品が存在しないことによって何らかの業務操作が実施できない場合に発生する復帰不能例外です.
 *
 * @author ryozo
 */
public class ProductNotExistsException extends SystemException {
    
    private static final long serialVersionUID = 5624349203921967181L;

    public ProductNotExistsException() {
        this(null);
    }

    public ProductNotExistsException(Throwable cause) {
        super(SysErrorMessageCodeEnum.ERROR_PRODUCT_NOTEXIST, cause);
    }
}
