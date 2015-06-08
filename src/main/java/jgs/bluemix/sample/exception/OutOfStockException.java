package jgs.bluemix.sample.exception;

import jgs.bluemix.sample.message.MessageCodeEnum;

/**
 * 商品の在庫が無いことに起因して、何らかの操作が実施できない場合に発生する業務例外です.
 * 例えば、購入確認画面の表示から完了までの間に他ユーザによって購入が行われ、
 * 当該商品の在庫数が0になった場合等に当例外が発生します.<br>
 * なお、当システムの商品検索機能は全て在庫が存在する商品のみを対象としているため、
 * 画面表示時点では購入可能な商品のみが表示されます.
 *
 * @author ryozo
 */
public class OutOfStockException extends BusinessException {
    private static final long serialVersionUID = -3193112286387806500L;

    public OutOfStockException() {
        this(null);
    }

    public OutOfStockException(Throwable cause) {
        super(MessageCodeEnum.ERROR_OUTOFSTOCK, cause);
    }
}
