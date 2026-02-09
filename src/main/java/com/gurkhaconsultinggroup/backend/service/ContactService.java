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

    public ContactRequest getContactRequestById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public ContactRequest updateContactRequest(Long id, ContactRequest contactRequest) {
        ContactRequest existingContactRequest = contactRepository.findById(id).orElse(null);
        if (existingContactRequest != null) {
            existingContactRequest.setName(contactRequest.getName());
            existingContactRequest.setEmail(contactRequest.getEmail());
            existingContactRequest.setInterest(contactRequest.getInterest());
            existingContactRequest.setMessage(contactRequest.getMessage());
            return contactRepository.save(existingContactRequest);
        }
        return null;
    }

    public void deleteContactRequest(Long id) {
        contactRepository.deleteById(id);
    }
}
