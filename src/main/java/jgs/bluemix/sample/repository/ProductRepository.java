package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.ProductPic;
import jgs.bluemix.sample.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品関連の操作を提供するRepositoryです.
 *
 * @author ryozo
 */
@Repository
public class ProductRepository {

    @Autowired
    ProductMapper productMapper;

    /**
     * 全ての在庫品を検索します.
     * Productの一覧はProductCodeの昇順で返却します。
     *
     * @return 在庫品の一覧
     */
    public List<Product> findAllStockProducts() {
        return productMapper.findAllStockProducts();
    }

    /**
     * 指定されたProductCodeを保持する商品を検索します.
     *
     * @param productCode 検索条件とするProductCode
     * @return 検索結果
     */
    public Product findProductByProductCode(String productCode) {
        return productMapper.findProductByProductCode(productCode);
    }

    /**
     * 指定された商品コード(ProductCode)を保持する商品の画像(PIC)を保持する{@link ProductPic}インスタンスを返却します.
     *
     * @param productCode 検索対象商品の商品コード
     * @return 商品画像を保持するProductPicインスタンス
     */
    public ProductPic findProductPic(String productCode) {
        return productMapper.findProductPic(productCode);
    }

}
