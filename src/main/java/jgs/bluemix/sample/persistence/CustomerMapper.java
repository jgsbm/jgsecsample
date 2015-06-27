package jgs.bluemix.sample.persistence;

import jgs.bluemix.sample.entity.CreditCard;
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

    /**
     * 引数に指定されたクレジットカードを追加します.
     * @param creditCard 追加対象クレジットカード情報
     */
    void insertCreditCard(CreditCard creditCard);
    
    /**
     * 引数に指定されたユーザを更新します.
     * @param customer 更新対象ユーザ
     */
    void updateCustomer(Customer customer);
    
    /**
     * 引数に指定されたクレジットカードを更新します.
     * @param creditCard 更新対象クレジットカード情報
     */
    void updateCreditCard(CreditCard creditCard);
    
    /**
     * 引数に指定されたユーザのパスワードを更新します.
     * @param customer 更新対象ユーザ
     */
    void updatePassword(Customer customer);
}
