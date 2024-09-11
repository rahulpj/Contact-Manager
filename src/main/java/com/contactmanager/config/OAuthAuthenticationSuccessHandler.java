package com.contactmanager.config;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.util.*;

import com.contactmanager.entities.Providers;
import com.contactmanager.entities.User;
import com.contactmanager.helpers.AppConstatnts;
import com.contactmanager.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.OverridesAttribute;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
            logger.info("OAuthAuthenticationSuccessHandler");


            // identify the author

            var oAuth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;
            
            String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            logger.info(authorizedClientRegistrationId);

            var oauthuser = (DefaultOAuth2User)authentication.getPrincipal();

            oauthuser.getAttributes().forEach((key,value)->{
                logger.info(key+" : "+value);
            });

            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setRoleList(List.of(AppConstatnts.ROLE_USER));
            user.setEmailVerified(true);
            user.setEnabled(true);
            user.setPassword("password");
            

            if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
                // google
                //google attributes
                user.setEmail(oauthuser.getAttribute("email").toString());
                user.setProfilePic(oauthuser.getAttribute("picture").toString());
                user.setName(oauthuser.getAttribute("name"));
                user.setProviderUserId(oauthuser.getName());
                user.setProvider(Providers.GOOGLE);
                user.setAbout("This account is created by google");



            }else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                // github
                // github attributes
                String email=oauthuser.getAttribute("email")!=null?oauthuser.getAttribute("email").toString():oauthuser.getAttribute("login").toString()+"@gmail.com";
                String picture=oauthuser.getAttribute("avatar_url").toString();
                String name=oauthuser.getAttribute("login").toString();
                String providerUserId=oauthuser.getName();
                user.setEmail(email);
                user.setProfilePic(picture);
                user.setName(name);
                user.setProviderUserId(providerUserId);
                user.setProvider(Providers.GITHUB);
                user.setAbout("This account is created by github");

            }


            // DefaultOAuth2User user= (DefaultOAuth2User) authentication.getPrincipal();

            // // logger.info(user.getName());

            // // user.getAttributes().forEach((key,value)->{
            // //     logger.info("{}:{}",key,value);
            // // });

            // // logger.info(user.getAuthorities().toString());
            
            // // data database save

            // String email=user.getAttribute("email").toString();
            // String name=user.getAttribute("name").toString();
            // String picture=user.getAttribute("picture").toString();

            // //create user and save it to db

            // User user1 = new User();
            // user1.setEmail(email);
            // user1.setName(name);
            // user1.setProfilePic(picture);
            // user1.setPassword("password");
            // user1.setUserId(UUID.randomUUID().toString());
            // user1.setProvider(Providers.GOOGLE);
            // user1.setEnabled(true);
            // user1.setEmailVerified(true);
            // user1.setProviderUserId(user.getName());
            // user1.setRoleList(List.of(AppConstatnts.ROLE_USER));
            // // userRepo.save(user1);

            User user3=userRepo.findByEmail(user.getEmail()).orElse(null);
            {
                if(user3==null) userRepo.save(user);
                logger.info("User saved");
            }


            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
        
    }

}
