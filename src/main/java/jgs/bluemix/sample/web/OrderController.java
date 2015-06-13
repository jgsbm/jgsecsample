package jgs.bluemix.sample.web;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.exception.OutOfStockException;
import jgs.bluemix.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * 注文操作と関連する機能を提供するControllerです.
 *
 * @author ryozo
 */
@Controller
public class OrderController {

    @Autowired
    ProductService productService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "itemCode") String itemCode, Model model) {
        Product product = productService.findStockProductByItemCode(itemCode);
        model.addAttribute("product", product);
        return "detail";
    }

    @RequestMapping("/register")
    public String register(@RequestParam(value = "itemCode") String itemCode, Model model) {
        // Modelの情報を再取得してレジ画面を表示する
        Product product = productService.findStockProductByItemCode(itemCode);
        model.addAttribute("product", product);
        return "register";
    }

    @ExceptionHandler(OutOfStockException.class)
    public ModelAndView handleException(OutOfStockException exception, Locale locale) {
        Product outOfStockProduct = productService.findProductByItemCode(exception.getItemCode());
        ModelAndView mav = new ModelAndView("/detail");
        mav.addObject("hasErrorMessage", Boolean.TRUE);
        mav.addObject("errorMessage", messageSource.getMessage(exception.getMessageCode().getCode(),
                new String[] {outOfStockProduct.getItemName()}, locale));
        mav.addObject(outOfStockProduct);
        return mav;
    }
}

