package com.nnk.springboot.config;

import com.nnk.springboot.security.UserAuthenticationSuccessHandler;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * The type Spring security config.
 */
@Configuration
@EnableWebSecurity // tell spring tu surpass security auto-configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //prePostEnabled should be true to use @PreAuthorized annotation
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorize -> {
                    authorize.antMatchers("/")
                            .permitAll();
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()//generate a login page automatically
                .successHandler(authenticationSuccessHandler()) // customise success authentication handler
                .and()
                .httpBasic()
                .and()
                .csrf()//Cross Site Request Forgery attacks
                .disable() //disable CSRF protection (enable by default)
                .exceptionHandling()
                //.accessDeniedPage("/user/**") forbidden page -> return 404 error
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") //redirect to this page after logout
        ;

    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //encode password using BCrypt
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication success handler authentication success handler.
     *
     * @return the authentication success handler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        //custom success authentication handler
        return new UserAuthenticationSuccessHandler();
    }
}
