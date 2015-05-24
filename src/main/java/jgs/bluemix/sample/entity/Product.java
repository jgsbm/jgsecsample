package jgs.bluemix.sample.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * Product情報を表現するEntityです。
 *
 * @author ryozo
 */
@Data
public class Product extends BaseEntity {
    private String itemCode;
    private String itemName;
    private BigDecimal price;
    private Blob pic;
    private String detail;
    private Stock stock;
}
