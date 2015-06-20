package jgs.bluemix.sample.web;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

/**
 * 顧客情報の入力フォームです.
 *
 * @author ryozo
 */
@Data
public class OrderForm {

    @NotNull
//    @Size(min = 1, max = 10)
//    @Min(1)
    private Integer quantity;
}
