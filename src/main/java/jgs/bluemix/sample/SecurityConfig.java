package jgs.bluemix.sample;

import jgs.bluemix.sample.crypto.HexEncodingTextEncryptor;
import jgs.bluemix.sample.crypto.LesserAesBytesEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@PropertySource("classpath:ecsecurity.properties")
public class SecurityConfig {

    @Autowired
    Environment environment;

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity security) throws Exception {
            security.authorizeRequests().antMatchers("/index", "/userRegister", "/userConfirm")
                    .permitAll().anyRequest().authenticated();

            security.formLogin().loginProcessingUrl("/login").loginPage("/index")
                    .failureUrl("/index?error").defaultSuccessUrl("/menu", true)
                    .usernameParameter("email").passwordParameter("password");

            security.logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                    .logoutSuccessUrl("/index");
        }

        @Override
        public void configure(WebSecurity security) throws Exception {
            security.ignoring().antMatchers("/webjars/**", "/css/**", "/images/**", "/js/**");
        }
    }

    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserDetailsService userDetailService;

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailService)
                    .passwordEncoder(passwordEncoder());
        }
    }
    /**
     * 文字列用途のEncryptorを生成します.
     * @return
     */
    @Bean
    public TextEncryptor textEncryptor() {
        BytesEncryptor bytesEncryptor = new LesserAesBytesEncryptor(
                environment.getProperty("ecsecurity.pass"),
                environment.getProperty("ecsecurity.salt"));
        return new HexEncodingTextEncryptor(bytesEncryptor);
    }
}