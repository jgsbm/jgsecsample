package jgs.bluemix.sample;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@Configuration
public class AppConfig {

    // for フォーム文字化け
    // http://blog.scheakur.com/post/92294343912/spring=boot-utf-8
    @Order(Ordered.HIGHEST_PRECEDENCE)
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

    /**
     * {@link MessageSource}の定義
     * @return 本システムで利用するMessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message/application-messages");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

}
