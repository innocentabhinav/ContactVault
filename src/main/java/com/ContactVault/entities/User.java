package com.ContactVault.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.*;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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


    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //Sign-in Self ,Google , Facebook , github etc
    @Enumerated(value = EnumType.STRING)
    private Providers provider=Providers.SELF;

    private String providerUserId;

    //CascadeType  means here that if user is deleted then its conatact get deleted or added then its get added.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();



}
