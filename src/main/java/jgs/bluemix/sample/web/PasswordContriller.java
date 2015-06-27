package jgs.bluemix.sample.web;

import java.util.Locale;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.entity.LoginUser;
import jgs.bluemix.sample.message.BusinessMessageCodeEnum;
import jgs.bluemix.sample.service.MemberService;
import jgs.bluemix.sample.validation.PasswordPasswordEqualsValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * パスワード更新用のControllerです。
 * 
 * @author takeshimikami
 */
@Controller
@RequestMapping
public class PasswordContriller {

    @Autowired
    PasswordPasswordEqualsValidator passwordValidator;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;

    @Autowired
    MessageSource messageSource;

    @ModelAttribute
    public PasswordForm initForm() {
	return new PasswordForm();
    }

    @RequestMapping("/password")
    public String password() {
	return "password";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(@Validated PasswordForm form, BindingResult result,
	    @AuthenticationPrincipal LoginUser loginUser, Model model, UriComponentsBuilder uriBuilder, Locale locale) {

	passwordValidator.validate(form, result);

	if (result.hasErrors()) {
	    return password();
	}

	Customer customer = memberService.loadCustomerByUsername(loginUser.getCustomer().getEmail());

	boolean checkPassword = passwordEncoder.matches(form.getUsePassword(), loginUser.getCustomer().getHashedPassword());
	if (!checkPassword) {
	    model.addAttribute("hasErrorMessage", Boolean.TRUE);
	    String message = messageSource.getMessage(BusinessMessageCodeEnum.ERROR_FAILED_PASSWORD.getCode(), new String[] {},
		    locale);
	    model.addAttribute("errorMessage", message);
	    return password();
	}
	// パスワードハッシュ化
	customer.setHashedPassword(passwordEncoder.encode(form.getPassword()));
	Customer updatedCustomer = memberService.updatePassword(customer);

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	LoginUser updatedLoginUser = loginUser;
	updatedLoginUser.setCustomer(updatedCustomer);

	Authentication newAuth = new UsernamePasswordAuthenticationToken(updatedLoginUser, auth.getCredentials(),
		auth.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(newAuth);

	uriBuilder.path("/menu");
	uriBuilder.queryParam("messageCode", BusinessMessageCodeEnum.INFO_UPDATE_CUSTOMER_SUCCESS.getCode());
	return "redirect:" + uriBuilder.build().toUriString();
    }
}
