package jgs.bluemix.sample.web;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.message.BusinessMessageCodeEnum;
import jgs.bluemix.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

/**
 * Menu画面と関連する操作を受け付けるControllerです。
 *
 * @author ryozo
 */
@Controller
public class MenuController {

    @Autowired
    ProductService productService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping("/menu")
    public String menu(@RequestParam(value = "orderedCode", required = false) String orderedCode,@RequestParam(value = "messageCode", required = false) String messageCode,
                       Model model, Locale locale) {
        List<Product> products = productService.findAllStockProducts();
        model.addAttribute("products", products);
        if (StringUtils.hasLength(orderedCode)) {
            model.addAttribute("hasInfoMessage", Boolean.TRUE);
            model.addAttribute("infoMessage", messageSource.getMessage(
                    BusinessMessageCodeEnum.INFO_ORDER_SUCCESS.getCode(), new String[]{orderedCode}, locale));
        }
        if (StringUtils.hasLength(messageCode)) {
            model.addAttribute("hasInfoMessage", Boolean.TRUE);
            model.addAttribute("infoMessage", messageSource.getMessage(
        	    messageCode, new String[]{}, locale));
        }
        return "menu";
    }
}
