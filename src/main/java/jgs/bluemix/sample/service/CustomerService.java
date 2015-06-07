package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.exception.AlreadyUserRegistedException;
import jgs.bluemix.sample.repository.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 顧客関連のビジネスロジックを提供するサービスです.
 *
 * @author ryozo
 */
@Service
public class CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 引数のユーザを新規顧客として登録します.
     *
     * @param customer 新規顧客
     * @return 登録結果(登録時に発行される各種情報を含むCsutomerインスタンス)
     */
    @Transactional
    public Customer signup(Customer customer) {
        try {
            customerMapper.insertCustomer(customer);
        } catch (DuplicateKeyException e) {
            // UserIDの重複(既に登録済のユーザ)
            // TODO AOPによるログ出力
            throw new AlreadyUserRegistedException(e);
        }
        return customer;
    }

}
