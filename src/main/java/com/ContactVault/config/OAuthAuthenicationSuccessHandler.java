package com.ContactVault.config;

import com.ContactVault.entities.Providers;
import com.ContactVault.entities.User;
import com.ContactVault.helpers.AppConstants;
import com.ContactVault.repositories.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("OAuthAuthenicationSuccessHandler");

       var oAuth2User= (DefaultOAuth2User)authentication.getPrincipal();

        //identifying provider
        var oauth2AuthenticationToken= (OAuth2AuthenticationToken)authentication;
        String authorizedClientRegistrationId=oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

        User user=new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        
        if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
            //google
            //google-attributes

            user.setEmail(oAuth2User.getAttribute("email").toString());
            user.setProfilePic(oAuth2User.getAttribute("picture").toString());
            user.setName(oAuth2User.getAttribute("name").toString());
            user.setProviderUserId(oAuth2User.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using google.");


        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            //github
            //github-attributes

            String email=oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email").toString() : oAuth2User.getAttribute("login").toString()+"@gmail.com";
            String picture=oAuth2User.getAttribute("avatar_url").toString();
            String name = oAuth2User.getAttribute("login").toString();
            String providerUserId = oAuth2User.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("This account is created using github");

        }else{
            logger.info("OAuthAuthenicationSuccessHandler : Unknown Provider");
        }

        //save the user
        User databaseUser = userRepo.findByEmail(user.getEmail()).orElse(null);
         if (databaseUser == null) {
         userRepo.save(user);
         logger.info("User saved:" + user.getEmail());
         }

//To see the attributes and its value when we sign in through login provider
//        DefaultOAuth2User user  = (DefaultOAuth2User) authentication.getPrincipal();
//
//        logger.info(user.getName());
//
//        user.getAttributes().forEach((key, value) -> {
//            logger.info(key + " : " + value);
//        });
//
//         logger.info(user.getAuthorities().toString());


        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");


    }



}
