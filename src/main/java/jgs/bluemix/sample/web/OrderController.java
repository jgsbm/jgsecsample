package jgs.bluemix.sample.web;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 注文操作と関連する機能を提供するControllerです.
 *
 * @author ryozo
 */
@Controller
public class OrderController {

    @Autowired
    ProductService productService;

    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "itemCode") String itemCode, Model model) {
        Product product = productService.findProductByItemCode(itemCode);
        model.addAttribute("product", product);
        return "detail";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}

