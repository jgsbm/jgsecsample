package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.ProductPic;
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
     * 指定した商品コードを保持する商品の画像を保持する{@link ProductPic}インスタンスを返却します.
     * @return 商品画像を保持するProductPicインスタンス
     */
    public ProductPic findProductPic(String itemCode) {
        return productMapper.findProductPic(itemCode);
    }
}
