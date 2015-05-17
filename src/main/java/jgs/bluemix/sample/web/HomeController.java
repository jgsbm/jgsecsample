package jgs.bluemix.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String home() {
        return "index";
    }
    
    @RequestMapping("/menu")
    public String top() {
        return "menu";
    }
    
    @RequestMapping("/detail")
    public String detail() {
        return "detail";
    }
    
    @RequestMapping("/member")
    public String member() {
        return "member";
    }
    
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
