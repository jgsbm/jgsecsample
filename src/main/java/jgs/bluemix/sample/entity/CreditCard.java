package jgs.bluemix.sample.entity;

import lombok.Data;
import lombok.ToString;

/**
 * CreditCard情報を表現するEntityです.
 *
 * @author ryozo
 */
@Data
@ToString(callSuper = true, exclude = "customer")
public class CreditCard extends BaseEntity {
    private String encryptedCreditno;
    private Customer customer;
}
