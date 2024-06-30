package com.ContactVault.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {
            //if logged in with google then fetching email
            var oAuth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;
            var clientId=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Getting email from Google");
                username = oauth2User.getAttribute("email").toString();

            } else if (clientId.equalsIgnoreCase("github")) {
                //if logged in with github then fetching email
                System.out.println("Getting email from Github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            return username;

        } else {
            //if logged in with email and password then extracting email
            System.out.println("Getting data i.e. email from local database");
            return authentication.getName();
        }

    }

}
