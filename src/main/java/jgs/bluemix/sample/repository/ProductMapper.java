package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.Product;

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

}
