package com.ContactVault.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name = "username",nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;
    @Column(length = 1000)

    private String about;
    @Column(length = 1000)

    private String profilePic;
    private String phoneNumber;


    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //Sign-in Self ,Google , Facebook , github etc
    @Enumerated(value = EnumType.STRING)
    private Providers provider=Providers.SELF;

    private String providerUserId;

    //CascadeType  means here that if user is deleted then its conatact get deleted or added then its get added.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //list of roles(User or Admin)
        //converted to collection of simpleGrantAuthority[roles(ADMIN,USER)]
        Collection<SimpleGrantedAuthority> roles= roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email; //since for our project email is our username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



}
