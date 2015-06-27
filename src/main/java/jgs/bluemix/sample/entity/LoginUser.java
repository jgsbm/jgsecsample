package jgs.bluemix.sample.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * LoginUserを表現するクラスです.
 * 当クラスは{@link Customer}クラスをラップし、SpringSecurityによってログインユーザ情報を保持するEntityとして利用されます.
 *
 * @author ryozo
 */
@Data
public class LoginUser extends User {

    private Customer customer;

    public LoginUser(Customer customer) {
        super(customer.getEmail(), customer.getHashedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.customer = customer;
    }

}
