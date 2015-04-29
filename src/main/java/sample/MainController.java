package sample;

import org.springframework.stereotype.Controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.SpringApplication;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
public class MainController 
{
    @RequestMapping("/")
    public String home() {
        return "index";
    }


    // for フォーム文字化け 
    // http://blog.scheakur.com/post/92294343912/spring=boot-utf-8
    @Bean
    public Filter characterEncodingFilter() {
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-8");
      filter.setForceEncoding(true);
      return filter;
    }

    // エラーメッセージのカスタマイズ(Spring Boot Reference 24.1.6)
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer factory) {
            //factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
            //factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/401"));
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            //factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
        }

    }

    @RequestMapping("/404")
    public String notFoundError() {
        return "error/404";
    }



    // an entry point
    public static void main( String[] args )
    {
        SpringApplication.run(MainController.class, args);

    }
}
