package com.contactmanager.helpers;
import java.security.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;



public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){
        // login with email
        

        if(authentication instanceof OAuth2AuthenticationToken){


            var aOAuth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;
            var clientId=aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oAuth2User=(OAuth2User)authentication.getPrincipal();
            String username="";
            if(clientId.equalsIgnoreCase("google")){
                // login with google
                System.out.println("getting email from google");
                username=oAuth2User.getAttribute("email").toString();

            }else if(clientId.equalsIgnoreCase("github")){
                // login with github
                System.out.println("getting email from github");
                username=oAuth2User.getAttribute("email")!=null?oAuth2User.getAttribute("email").toString():oAuth2User.getAttribute("login").toString()+"@gmail.com";

            }
            return username;
    
        }else{
            return authentication.getName();
        }
        
        
    }
}
