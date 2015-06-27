package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.CreditCard;
import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.persistence.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 顧客関連の操作を提供するRepositoryです.
 *
 * @author ryozo
 */
@Repository
public class CustomerRepository {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 引数に指定されたメールアドレスを保持するユーザを検索します.
     * @param email 検索条件とするメールアドレス
     * @return 検索結果
     */
    public Customer findCustomerByMail(String email) {
        return customerMapper.findCustomerByMail(email);
    }

    /**
     * 引数に指定されたクレジットカードを追加します.
     * @param creditCard 追加対象クレジットカード情報
     */
    public void insertCreditCard(CreditCard creditCard) {
        customerMapper.insertCreditCard(creditCard);
    }

    /**
     * 引数に指定されたユーザを追加します.
     * @param customer 追加対象ユーザ
     */
    public void insertCustomer(Customer customer) {
        customerMapper.insertCustomer(customer);
    }
    
    /**
     * 引数に指定されたユーザを更新します.
     * @param customer 更新対象ユーザ
     */
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }
    
    /**
     * 引数に指定されたクレジットカード更新します.
     * @param creditCard 更新対象クレジットカード情報
     */
    public void updateCreditCard(CreditCard creditCard) {
        customerMapper.updateCreditCard(creditCard);
    }
    
    /**
     * 引数に指定されたユーザのパスワードを更新します.
     * @param customer 更新対象ユーザ
     */
    public void updatePassword(Customer customer) {
        customerMapper.updatePassword(customer);
    }

}
