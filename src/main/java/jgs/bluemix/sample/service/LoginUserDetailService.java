package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.entity.LoginUser;
import jgs.bluemix.sample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ログインユーザ情報を取得するためのServiceクラスです.
 * 当クラスはSpring-Securityによってログインユーザの取得用serviceとして利用されます.
 */
@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }
        Customer customer = customerRepository.findCustomerByMail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        return new LoginUser(customer);
    }

}
