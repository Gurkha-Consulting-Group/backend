package com.gurkhaconsultinggroup.backend.controller;

import com.gurkhaconsultinggroup.backend.model.ContactRequest;
import com.gurkhaconsultinggroup.backend.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/contact")
@Tag(name = "Contact Request API",description = "API to get all requests, save contact request")
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping
    public List<ContactRequest> getAllContactRequests(){
        return contactService.getAllContactRequests();
    }

    @PostMapping
    public ContactRequest saveContactRequest(@RequestBody ContactRequest contactRequest){
        return contactService.saveContactRequest(contactRequest);
    }

    @GetMapping("/{id}")
    public ContactRequest getContactRequestById(@PathVariable Long id){
        return contactService.getContactRequestById(id);
    }

    @PutMapping("/{id}")
    public ContactRequest updateContactRequest(@PathVariable Long id, @RequestBody ContactRequest contactRequest) {
        return contactService.updateContactRequest(id, contactRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteContactRequest(@PathVariable Long id) {
        contactService.deleteContactRequest(id);
    }
}
