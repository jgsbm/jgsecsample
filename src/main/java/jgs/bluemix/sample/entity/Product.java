package jgs.bluemix.sample.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Product情報を表現するEntityです。
 *
 * @author ryozo
 */
@Data
@ToString(callSuper = true)
public class Product extends BaseEntity {
    private String itemCode;
    private String itemName;
    private BigDecimal price;
    private String detail;
    private ProductPic productPic;
    private Stock stock;
}
