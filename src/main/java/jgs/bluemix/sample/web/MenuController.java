package jgs.bluemix.sample.web;

import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Menu画面と関連する操作を受け付けるControllerです。
 *
 * @author ryozo
 */
@Controller
public class MenuController {

    @Autowired
    ProductService productService;

    @RequestMapping("/menu")
    public String menu(Model model) {
        List<Product> products = productService.findAllStockProducts();
        model.addAttribute("products", products);
        return "menu";
    }
}
