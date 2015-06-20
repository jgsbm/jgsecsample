package jgs.bluemix.sample.entity;

import lombok.Data;

/**
 * CreditCard情報を表現するEntityです.
 *
 * @author ryozo
 */
@Data
public class CreditCard extends BaseEntity {
    private String encryptedCreditno;
    private Customer customer;
}
