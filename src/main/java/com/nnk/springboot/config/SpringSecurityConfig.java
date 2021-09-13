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
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The User details service.
     */
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
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
                .formLogin()
                .successHandler(authenticationSuccessHandler()) // customise success authentication handler
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                //.accessDeniedPage("/user/**") forbidden page -> return 404 error
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        ;

    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication success handler authentication success handler.
     *
     * @return the authentication success handler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new UserAuthenticationSuccessHandler();
    }
}
