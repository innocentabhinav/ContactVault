package com.ContactVault.services;

import com.ContactVault.entities.Contact;
import com.ContactVault.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContactService {

//    save Contact
    Contact SaveContact(Contact contact);

    //update Contact
    Contact updateContact(Contact contact);

    //get All Contacts
    List<Contact> getAll(Contact contact);

    // get contact id
    Contact getById(String id);

    void deleteContact(String id);

    //search Contact
    List<Contact> search(String name, String email ,String phoneNumber);

    //get contacts by id
    List<Contact> getByUserid(String userId);

    Page<Contact> getByUser(User user ,int page ,int size ,String sortField , String sortDirection);




}
