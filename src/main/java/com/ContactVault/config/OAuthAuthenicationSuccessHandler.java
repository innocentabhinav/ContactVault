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
        DefaultOAuth2User user  = (DefaultOAuth2User) authentication.getPrincipal();

//        logger.info(user.getName());
//
//        user.getAttributes().forEach((key, value) -> {
//            logger.info(key + " : " + value);
//        });
//
//         logger.info(user.getAuthorities().toString());

         // data database save:

         String email = user.getAttribute("email").toString();
         String name = user.getAttribute("name").toString();
         String picture = user.getAttribute("picture").toString();

         // create oauthUser and saving oauth user info in database

        User oauthUser = new User();
        oauthUser.setEmail(email);
        oauthUser.setName(name);
        oauthUser.setProfilePic(picture);
        oauthUser.setPassword("password");
        oauthUser.setUserId(UUID.randomUUID().toString());
        oauthUser.setProvider(Providers.GOOGLE);
        oauthUser.setEnabled(true);

        oauthUser.setEmailVerified(true);
        oauthUser.setProviderUserId(user.getName());
        oauthUser.setRoleList(List.of(AppConstants.ROLE_USER));
        oauthUser.setAbout("This account is created using google..");

         User databaseUser = userRepo.findByEmail(email).orElse(null);
         if (databaseUser == null) {
         userRepo.save(oauthUser);
         logger.info("User saved:" + email);
         }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");


    }



}
