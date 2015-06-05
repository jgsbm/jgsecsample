package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.Customer;

/**
 * Customerテーブルに関する操作を提供するMapperクラスです.
 *
 * @author ryozo
 */
public interface CustomerMapper {

    /**
     * 引数に指定されたメールアドレスを保持するユーザを検索します.
     * @param email 検索条件とするメールアドレス
     * @return 検索結果
     */
    Customer findCustomerByMail(String email);

    /**
     * 引数に指定されたユーザを追加します.
     * @param customer 追加対象ユーザ
     */
    void insertCustomer(Customer customer);
}
