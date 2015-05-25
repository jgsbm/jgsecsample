package jgs.bluemix.sample.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * Stock情報を表現するEntityです。
 *
 * @author ryozo
 */
@Data
@ToString(exclude = "product")
public class Stock extends BaseEntity {
    private Integer stock;
    private Product product;
}
