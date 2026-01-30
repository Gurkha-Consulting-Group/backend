package com.gurkhaconsultinggroup.backend.service;

import com.gurkhaconsultinggroup.backend.model.ContactRequest;
import com.gurkhaconsultinggroup.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    public List<ContactRequest> getAllContactRequests()
    {
        return contactRepository.findAll();
    }

    public ContactRequest saveContactRequest(ContactRequest contactRequest) {
       return contactRepository.save(contactRequest);
    }
}
