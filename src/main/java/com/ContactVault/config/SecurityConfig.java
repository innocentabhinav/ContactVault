package com.ContactVault.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    //user create and login using java code inMemory service
    @Bean
    public UserDetailsService userDetailsService(){

//    Creating User
       UserDetails user1= User
               .withDefaultPasswordEncoder()
               .username("admin")
               .password("admin")
               .roles("ADMIN","USER")
               .build();

        var inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user1);
        return inMemoryUserDetailsManager;
    }


}
