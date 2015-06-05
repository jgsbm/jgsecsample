package jgs.bluemix.sample.web;

import jgs.bluemix.sample.validation.CustomerPasswordEqualsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 顧客と関連する機能を提供するControllerです.
 *
 * @author ryozo
 */
@Controller
@RequestMapping
public class CustomerController {

    @Autowired
    CustomerPasswordEqualsValidator passwordValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
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
    public String userConfirm(@Validated CustomerForm customer, BindingResult result) {
        if (result.hasErrors()) {
            return userRegister();
        }
        return null;
    }

}
