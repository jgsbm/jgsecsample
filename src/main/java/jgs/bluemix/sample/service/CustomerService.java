package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.repository.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Customer signup(Customer customer) {
        customerMapper.insertCustomer(customer);
        return customer;
    }

}
