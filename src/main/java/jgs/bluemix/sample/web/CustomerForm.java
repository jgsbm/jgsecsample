package jgs.bluemix.sample.web;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 顧客情報の入力フォームです.
 * 当クラスの{@code toString()}はセキュリティ確保のため、所定項目を除外したうえで文字列表現を返します.
 *
 * @author ryozo
 */
@Data
@ToString(exclude = {"password", "confirmPassword", "creditno"})
public class CustomerForm {
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
    
    @NotBlank
    @Size(min = 14, max = 16)
    private String creditno;

}
