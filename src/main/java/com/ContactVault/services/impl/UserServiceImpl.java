package com.ContactVault.services.impl;

import com.ContactVault.entities.User;
import com.ContactVault.helpers.ResourceNotFoundException;
import com.ContactVault.repositories.UserRepo;
import com.ContactVault.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //when we are going to save user , we need to generate a user id and set user id before saving the user .
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);
        //password encode
       // user.setPassword();
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User newUser=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        //update karenge newUser ko from user
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setAbout(user.getAbout());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setProfilePic(user.getProfilePic());
        newUser.setEnabled(user.isEnabled());
        newUser.setEmailVerified(user.isEmailVerified());
        newUser.setPhoneVerified(user.isPhoneVerified());
        newUser.setProvider(user.getProvider());
        newUser.setProviderUserId(user.getProviderUserId());
        //user info is extracted and set into newUser and now save it in the database.
        User save=userRepo.save(newUser);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
         User delUser=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
         userRepo.delete(delUser);
    }

    @Override
    public boolean isUserExist(String userId) {
        User userExistById=userRepo.findById(userId).orElse(null);
        return userExistById != null ? true : false ;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
         User userExistByEmail=userRepo.findByEmail(email).orElse(null);
         return userExistByEmail != null ? true : false ;

    }

    @Override
    public List<User> getAllUsers() {
         return userRepo.findAll();
    }
}
