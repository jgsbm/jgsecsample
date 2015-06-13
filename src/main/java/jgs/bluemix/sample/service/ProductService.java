package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.ProductPic;
import jgs.bluemix.sample.exception.OutOfStockException;
import jgs.bluemix.sample.exception.ProductNotFoundException;
import jgs.bluemix.sample.repository.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品関連のビジネスロジックを提供するサービスです.
 *
 * @author ryozo
 */
@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    /**
     * 全ての在庫品を検索します。
     * @return 在庫が1件以上存在する製品の一覧
     */
    public List<Product> findAllStockProducts() {
        return productMapper.findAllStockProducts();
    }

    /**
     * 引数に指定されたItemCodeを保持する在庫品を検索します.
     * 対象商品の在庫が存在しない場合{@link OutOfStockException}をthrowします.
     * @param itemCode 検索対象のItemCode
     * @return 検索結果
     */
    public Product findStockProductByItemCode(String itemCode) {
        Product product = productMapper.findProductByItemCode(itemCode);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        if (!isStock(product)) {
            throw new OutOfStockException(itemCode);
        }

        return product;
    }

    /**
     * 引数に指定されたItemCodを保持する商品を検索します(在庫品であるか在庫切れ品であるかを判定しません).
     * @param itemCode 検索対象のItemCode
     * @return 検索結果
     */
    public Product findProductByItemCode(String itemCode) {
        Product product = productMapper.findProductByItemCode(itemCode);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    /**
     * 指定した商品コードを保持する商品の画像を保持する{@link ProductPic}インスタンスを返却します.
     * @return 商品画像を保持するProductPicインスタンス
     */
    public ProductPic findProductPic(String itemCode) {
        return productMapper.findProductPic(itemCode);
    }

    /**
     * 在庫品であるか判定します.
     */
    private boolean isStock(Product product) {
        return product.getStock().getStock() > 0;
    }
}
