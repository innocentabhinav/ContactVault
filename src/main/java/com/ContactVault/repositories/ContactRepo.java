package com.ContactVault.repositories;

import com.ContactVault.entities.Contact;
import com.ContactVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {
    //find the contact by user

    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user.id=:userId")
    List<Contact> findByUserId(@Param("userId")String userId);


}
