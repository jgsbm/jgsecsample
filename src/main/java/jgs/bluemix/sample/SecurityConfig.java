package jgs.bluemix.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig {

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity security) throws Exception {
            security.authorizeRequests().antMatchers("/index", "/userRegister")
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
}