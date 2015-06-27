package jgs.bluemix.sample.web;

import jgs.bluemix.sample.entity.CreditCard;
import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.entity.LoginUser;
import jgs.bluemix.sample.message.BusinessMessageCodeEnum;
import jgs.bluemix.sample.service.MemberService;
import jgs.bluemix.sample.validation.CustomerEmailEqualsValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 顧客情報更新用のControllerです、
 * 
 * @author takeshimikami
 */
@Controller
@RequestMapping
public class MemberController {

    @Autowired
    TextEncryptor textEncryptor;

    @Autowired
    MemberService memberService;

    @ModelAttribute
    public MemberForm initForm() {
	return new MemberForm();
    }
    
    @RequestMapping("/member")
    public String getMember(@AuthenticationPrincipal LoginUser loginUser, Model model) {
	Customer customer = loginUser.getCustomer();
	model.addAttribute("member", customer);
	return "member";
    }

    @RequestMapping("/updateMember")
    public String updateMember(@Validated MemberForm form, BindingResult result, @AuthenticationPrincipal LoginUser loginUser, Model model,UriComponentsBuilder uriBuilder) {
	if (result.hasErrors()) {
	    return getMember(loginUser, model);
	}
	
	Customer customer = memberService.loadCustomerByUsername(loginUser.getCustomer().getEmail());
	Customer updatedCustomer = memberService.updateCustomer(mergeCustomer(customer, form));

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

    private Customer mergeCustomer(Customer customer, MemberForm form) {
	customer.setCustomerName(form.getCustomerName());
	customer.setAddress(form.getAddress());
	customer.setTel(form.getTel());
	customer.setEmail(form.getEmail());
	
	// TODO クレジットカード更新
//	if (!form.getCreditno().isEmpty()) {
//	    // クレジットカード番号の暗号化
//	    CreditCard creditCard = new CreditCard();
//	    creditCard.setEncryptedCreditno(textEncryptor.encrypt(form.getCreditno()));
//	    customer.setCreditCard(creditCard);
//	} else {
	    // 更新しないので仮でnullをセット
	    customer.setCreditCard(null);
//	}
	return customer;
    }
}
