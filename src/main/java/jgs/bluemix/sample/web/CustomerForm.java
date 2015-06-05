package jgs.bluemix.sample.web;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 顧客情報の入力フォームです.
 *
 * @author ryozo
 */
@Data
public class CustomerForm {
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;

    @NotBlank
    @Size(min = 1, max = 255)
    private String address;

    @NotBlank
    @Size(min = 1, max = 11)
    @Pattern(regexp = "^[0-9]+$", message = "電話番号が不適切です（電話番号は数値のみで指定してください）")
    private String tel;

    @NotBlank
    @Size(min = 1, max = 256)
    @Email
    private String email;

    @NotBlank
    private String confirmEmail;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
