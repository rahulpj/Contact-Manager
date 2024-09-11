package com.contactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.contactmanager.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {

    @Autowired
    OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Autowired
    SecurityCustomUserDetailService securityCustomUserDetailService;

    // Configuration of Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detailservice ka object
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService );
        // password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //configuration

        //configure urls that which one should be public and which should be secured
        httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        
        });

        // form default login
        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/dashboard", true);
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        // oauth configurations

        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(oAuthAuthenticationSuccessHandler);
        });

        return httpSecurity.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
}
