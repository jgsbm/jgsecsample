package jgs.bluemix.sample.validation;

import jgs.bluemix.sample.web.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Emailと確認用Emailの比較チェックを行うためのValidatoです.
 *
 * @author ryozo
 */
@Component
public class CustomerEmailEqualsValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerForm.class.isAssignableFrom(clazz);
    }

    /**
     * 指定されたEmailと確認用Emailが合致するかチェックします.
     *
     * @param target 入力値を保持するオブジェクト
     * @param errors 判定結果
     */
    @Override
    public void validate(Object target, Errors errors) {
        CustomerForm form = (CustomerForm)target;
        String email = form.getEmail();
        String confirmEmail = form.getConfirmEmail();

        if (isEmpty(email) || isEmpty(confirmEmail)) {
            // BeanValidator側でチェックするので当Validatorのチェック対象外
            return;
        }

        if (!email.equals(confirmEmail)) {
            errors.rejectValue("email", "customvalidation.error.emailNotEqual");
        }
    }
}
