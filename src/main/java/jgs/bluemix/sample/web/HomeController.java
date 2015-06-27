package jgs.bluemix.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String home() {
        return "index";
    }
    
    @RequestMapping("/customer")
    public String customer() {
        return "customer";
    }

}
