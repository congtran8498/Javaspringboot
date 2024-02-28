package com.example.fastfood.rest;

import com.example.fastfood.request.ContactRequest;
import com.example.fastfood.request.OrderStatusRequest;
import com.example.fastfood.request.UpsertRequest;
import com.example.fastfood.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactResource {
    @Autowired
    private ContactService contactService;
    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody ContactRequest request){
        return new ResponseEntity<>(contactService.create(request), HttpStatus.CREATED);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody OrderStatusRequest request){
        return ResponseEntity.ok(contactService.updateStatus(request));
    }
}
