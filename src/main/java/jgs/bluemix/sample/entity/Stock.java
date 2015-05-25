package jgs.bluemix.sample.entity;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * Stock情報を表現するEntityです。
 *
 * @author ryozo
 */
@Setter
@Getter
@ToString(exclude = "product")
public class Stock extends BaseEntity {
    private Integer stock;
    private Product product;
}
