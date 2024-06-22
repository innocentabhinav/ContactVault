package com.ContactVault.repositories;

import com.ContactVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
//    extra  methods db related operations
//    custom query methods


//    custom finder methods

Optional<User> findByEmail(String email);
Optional<User> findByEmailAndPassword(String email, String password);






}
