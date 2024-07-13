package com.ContactVault.services.impl;

import com.ContactVault.entities.Contact;
import com.ContactVault.entities.User;
import com.ContactVault.helpers.ResourceNotFoundException;
import com.ContactVault.repositories.ContactRepo;
import com.ContactVault.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact SaveContact(Contact contact) {
        String contactId= UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAll(Contact contact) {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not Found with given id :"+id));
    }

    @Override
    public void deleteContact(String id) {
        var contact =contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not Found with given id :"+id));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Contact> getByUserid(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(User user ,int page ,int size , String sortBy , String direction) {
        Sort sort= direction.equals("desc")?Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
        var pageable= PageRequest.of(page,size,sort);
        return contactRepo.findByUser(user,pageable);
    }
}

