package jgs.bluemix.sample.web;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 顧客情報の入力フォームです.
 * 
 * @takeshimikami
 */
@Data
public class PasswordForm {

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String usePassword;
}
