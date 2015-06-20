package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

/**
 * 在庫関連の操作を提供するRepositoryです.
 * <p>
 * TODO ServiceからRepositoryを使う場合とMapperを使う場合が混在してるから整理する（全てのServiceでRepository経由でMapperを呼び出す)
 *
 * @author ryozo
 */
@Repository
public class StockRepository {

    @Autowired
    ProductMapper productMapper;

    /**
     * 楽観的ロック制御付きの更新を行います.
     * ロック違反を検知した場合、{@link org.springframework.dao.OptimisticLockingFailureException}をthrowします.
     *
     * @param stock 更新対象のStock
     */
    public void optimisticUpdate(Stock stock) {
        if (!productMapper.isExistStock(stock)) {
            throw new EmptyResultDataAccessException("update target is not exist", 1);
        }
        int updateCount = productMapper.versionConditionalStockUpdate(stock);
        if (updateCount == 0) {
            String msg = String.format("Object of class[%s] with identifier(id) [%s]",
                    Stock.class.getName(), stock.getId());
            throw new OptimisticLockingFailureException(msg);
        }
    }

}
