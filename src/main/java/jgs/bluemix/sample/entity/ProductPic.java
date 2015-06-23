package jgs.bluemix.sample.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Productの画像情報を表現するEntityです.
 *
 * @author ryozo
 */
@Getter
@Setter
public class ProductPic extends BaseEntity {
    private byte[] pic;
    private Product product;
}
