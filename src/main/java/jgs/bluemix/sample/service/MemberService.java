package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.exception.UpdateUserException;
import jgs.bluemix.sample.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 顧客情報メンテナンスのビジネスロジックを提供するサービスです.
 *
 * @author takeshimikami
 */
@Service
public class MemberService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 引数のユーザを更新します.
     *
     * @param customer
     *            更新顧客
     * @return 更新結果(更新時に発行される各種情報を含むCsutomerインスタンス)
     */
    @Transactional
    public Customer updateCustomer(Customer customer) {
	try {
	    customerRepository.updateCustomer(customer);
	    if (customer.getCreditCard() != null) {
		customerRepository.updateCreditCard(customer.getCreditCard());
	    }
	} catch (DuplicateKeyException e) {
	    throw new UpdateUserException(e);
	}
	return customer;
    }

    /**
     * ユーザ情報を取得します。
     *
     * @param email
     *            メールアドレス
     * @return 顧客情報
     */
    @Transactional
    public Customer loadCustomerByUsername(String email) {
	return customerRepository.findCustomerByMail(email);
    }

    /**
     * 引数のユーザのパスワードを更新します.
     *
     * @param customer
     *            更新顧客
     * @return 更新結果(更新時に発行される各種情報を含むCsutomerインスタンス)
     */
    @Transactional
    public Customer updatePassword(Customer customer) {
	try {
	    customerRepository.updatePassword(customer);
	} catch (DuplicateKeyException e) {
	    throw new UpdateUserException(e);
	}
	return customer;
    }

}
