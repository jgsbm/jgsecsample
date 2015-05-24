package jgs.bluemix.sample.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * Stock情報を表現するEntityです。
 *
 * @author ryozo
 */
@Data
public class Stock extends BaseEntity {
    private Integer stock;
    private Product product;
}
