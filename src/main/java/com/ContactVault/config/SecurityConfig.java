package com.ContactVault.config;


import com.ContactVault.services.impl.SecurityCustomUserDetailsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    //user create and login using java code inMemory service
//    @Bean
//    public UserDetailsService userDetailsService(){
//
////    Creating User
//       UserDetails user1= User
//               .withDefaultPasswordEncoder()
//               .username("admin")
//               .password("admin")
//               .roles("ADMIN","USER")
//               .build();
//
//        var inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user1);
//        return inMemoryUserDetailsManager;
//    }


    /*Below we are doing that: config of authentication provider for spring security
      Whenever we are getting request for login with username and password
      user will be loaded from userDetailsService i.e what we have implemented in SecurityCustomUserDetailsService
      i.e. there it will load username from database if it exists and then authentication happens
      if the username coming from database and username we are passing at the time of login matches
      then user is logged in.
    */

    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        //user detail service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        //password encoder ka object is passed in below method using below defined passwordEncoder method
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //configured which url to be private and which to be public
        httpSecurity.authorizeHttpRequests(authorize->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //for now form default login , if want to configure can do here
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
