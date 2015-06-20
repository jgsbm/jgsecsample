package jgs.bluemix.sample.entity;

import lombok.Data;

/**
 * Customer情報を表現するEntityです.
 *
 * @author ryozo
 */
@Data
public class Customer extends BaseEntity {
    private static final long serialVersionUID = 7927199781832752718L;
    private String customerName;
    private String address;
    private String tel;
    private String hashedPassword;
    private String email;
    private CreditCard creditCard;
}
