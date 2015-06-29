package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.ProductPic;
import jgs.bluemix.sample.exception.OutOfStockException;
import jgs.bluemix.sample.exception.ProductNotExistsException;
import jgs.bluemix.sample.repository.ProductRepository;
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
    ProductRepository productRepository;

    /**
     * 全ての在庫品を検索します。
     * @return 在庫が1件以上存在する製品の一覧
     */
    public List<Product> findAllStockProducts() {
        return productRepository.findAllStockProducts();
    }

    /**
     * 引数に指定されたProductCmodeを保持する在庫品を検索します.
     * 対象商品の在庫が存在しない場合{@link OutOfStockException}をthrowします.
     * @param productCode productCode
     * @return 検索結果
     */
    public Product findStockProductByProductCode(String productCode) {
        Product product = productRepository.findProductByProductCode(productCode);
        if (product == null) {
            throw new ProductNotExistsException();
        }
        if (!isStock(product)) {
            throw new OutOfStockException(productCode);
        }

        return product;
    }

    /**
     * 引数に指定されたProductCodeを保持する商品を検索します(在庫品であるか在庫切れ品であるかを判定しません).
     * @param productCode 検索対象のproductCode
     * @return 検索結果
     */
    public Product findProductByProductCode(String productCode) {
        Product product = productRepository.findProductByProductCode(productCode);
        if (product == null) {
            throw new ProductNotExistsException();
        }
        return product;
    }

    /**
     * 指定した商品コードを保持する商品の画像を保持する{@link ProductPic}インスタンスを返却します.
     * @return 商品画像を保持するProductPicインスタンス
     */
    public ProductPic findProductPic(String productCode) {
        return productRepository.findProductPic(productCode);
    }

    /**
     * 在庫品であるか判定します.
     */
    private boolean isStock(Product product) {
        return product.getStock().getStock() > 0;
    }
}
