package jgs.bluemix.sample.persistence;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.ProductPic;
import jgs.bluemix.sample.entity.Stock;

import java.util.List;

/**
 * Productテーブル、および関連するテーブルへのアクセスを提供するMapperクラスです.
 *
 * @author ryozo
 */
public interface ProductMapper {

    /**
     * 全ての在庫品を検索します.
     * Productの一覧はProductCodeの昇順で返却します。
     */
    List<Product> findAllStockProducts();

    /**
     * 指定されたItemCodeを保持する商品を検索します.
     * @param itemCode 検索条件とするItemCode
     * @return 検索結果
     */
    Product findProductByItemCode(String itemCode);

    /**
     * 指定された商品コード(ItemCode)を保持する商品の画像(PIC)を保持する{@link ProductPic}インスタンスを返却します.
     *
     * @param itemCode 検索対象商品の商品コード
     * @return 商品画像を保持するProductPicインスタンス
     */
    ProductPic findProductPic(String itemCode);

    /**
     * 指定されたStockがDB上存在するか判定します.
     * @param stock
     * @return
     */
    boolean isExistStock(Stock stock);

    /**
     * 指定されたStockをUpdateします.
     * DB上のVersion列と引数のStockインスタンスのVersion列が一致している場合のみ更新が行われます.
     * @param stock 更新対象のStock
     * @return 更新件数
     */
    int versionConditionalStockUpdate(Stock stock);

}
