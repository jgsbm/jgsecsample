package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.ProductPic;

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
     * 指定された商品コード(ItemCode)を保持する商品の画像(PIC)を保持する{@link ProductPic}インスタンスを返却します.
     *
     * @param itemCode 検索対象商品の商品コード
     * @return 商品画像を保持するProductPicインスタンス
     */
    ProductPic findProductPic(String itemCode);

}
