package jgs.bluemix.sample.web;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 顧客情報の更新フォームです.
 *
 * @author mikami
 */
@Data
public class MemberForm {
    @NotBlank
    @Size(min = 1, max = 30)
    private String customerName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String address;

    @NotBlank
    @Size(min = 1, max = 11)
    @Pattern(regexp = "^[0-9]+$", message = "電話番号が不適切です（電話番号は数値のみで指定してください）")
    private String tel;

    @Size(min = 1, max = 256)
    @Email
    private String email;

    // TODO クレジットカード更新
//    @NotBlank
//    @Size(min = 14, max = 16)
//    private String creditno;
}
