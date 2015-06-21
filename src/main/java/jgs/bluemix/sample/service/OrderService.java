package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.Stock;
import jgs.bluemix.sample.exception.OutOfStockException;
import jgs.bluemix.sample.repository.ProductRepository;
import jgs.bluemix.sample.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注文関連のビジネスロジックを提供するサービスです.
 *
 * [注意]:
 *  当サービスクラスでは他サービスクラスのビジネスロジックを呼び出します.
 *  サービスクラス内で異なるサービスを利用するとはコードの見通しが悪くなるため本来は避けるべきです.
 *  複数のサービスから利用される可能性のあるビジネスロジックは、
 *  共通ビジネスロジックといった位置づけで明確に定義し、通常のサービスクラスとは分離して定義するべきです.
 *  当サンプルは規模が小さいため、Service間の呼び出しを実施しますが、
 *  相応の規模のシステムを構築する場合はサービス層でも共通サービスを意識した作りにしてください.
 *
 * @author ryozo
 */
@Service
public class OrderService {

    /* 本来は共通サービスを利用するべき.詳細はクラス側のJavaDoc参照 */
    @Autowired
    ProductService productService;

    @Autowired
    StockRepository stockRepository;

    /**
     * 商品の注文操作を実行します.
     * @param itemCode 注文対象の商品
     * @param orderQuantity 注文個数
     */
    @Transactional
    public void order(String itemCode, Integer orderQuantity) {
        // TODO 注文履歴の作成
        Product buyProduct = productService.findStockProductByItemCode(itemCode);
        Stock stock = buyProduct.getStock();

        if (stock.getStock() < orderQuantity) {
            // 購入数だけの在庫が存在しない場合
            // TODO 専用メッセージの設定（購入個数を減らせば購入できる可能性があることを示す）
            throw new OutOfStockException(itemCode);
        }

        Integer stockAfterOrder = stock.getStock() - orderQuantity;
        stock.setStock(stockAfterOrder);
        stockRepository.optimisticUpdate(stock);
    }
}
