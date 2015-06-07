package jgs.bluemix.sample.web;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.exception.BusinessException;
import jgs.bluemix.sample.service.CustomerService;
import jgs.bluemix.sample.validation.CustomerEmailEqualsValidator;
import jgs.bluemix.sample.validation.CustomerPasswordEqualsValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * 顧客と関連する機能を提供するControllerです.
 *
 * @author ryozo
 */
@Controller
@RequestMapping
public class CustomerController {

    @Autowired
    CustomerEmailEqualsValidator emailValidator;

    @Autowired
    CustomerPasswordEqualsValidator passwordValidator;

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
        binder.addValidators(passwordValidator);
    }

    @ModelAttribute
    public CustomerForm initForm() {
        return new CustomerForm();
    }

    @RequestMapping("/userRegister")
    public String userRegister() {
        return "userRegister";
    }

    @RequestMapping("/userConfirm")
    public String userConfirm(@Validated CustomerForm customerForm, BindingResult result, Model model, Locale locale) {
        if (result.hasErrors()) {
            return userRegister();
        }
        try {
            Customer customer = makeCustomerFromForm(customerForm);
            customerService.signup(customer);

        } catch (BusinessException e) {
            // TODO @ExceptionHandlerにする.
            String message = messageSource.getMessage(e.getMessageCode().getCode(),
                    new String[] {customerForm.getEmail()}, locale);
            model.addAttribute("hasErrorMessage", Boolean.TRUE);
            model.addAttribute("errorMessage", message);
            return userRegister();
        }

        return "/userConfirm";
    }

    /**
     * 引数に指定された{@link CustomerForm}から{@link Customer}インスタンスを作成します.
     * @param form 対象のCustomerForm
     * @return 内容が転記されたCusotmer
     */
    private Customer makeCustomerFromForm(CustomerForm form) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setHashedPassword(passwordEncoder.encode(form.getPassword()));
        return customer;
    }

}
