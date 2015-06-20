package jgs.bluemix.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String home() {
        return "index";
    }
    
    @RequestMapping("/member")
    public String member() {
        return "member";
    }
    
    @RequestMapping("/customer")
    public String customer() {
        return "customer";
    }

    @RequestMapping("/password")
    public String password() {
        return "password";
    }

}
