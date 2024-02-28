package com.example.fastfood.service;

import com.example.fastfood.entity.Contact;
import com.example.fastfood.entity.Order;
import com.example.fastfood.entity.User;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.ContactRepository;
import com.example.fastfood.repository.UserRepository;
import com.example.fastfood.request.ContactRequest;
import com.example.fastfood.request.OrderStatusRequest;
import com.example.fastfood.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    public Contact create(ContactRequest request){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        Contact contact = new Contact();
        contact.setUser(user);
        contact.setMessage(request.getMessage());
        contact.setStatus(Contact.Status.BLOCK);
        return contactRepository.save(contact);
    }
    public Page<Contact> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by("createdAt").descending());
        return contactRepository.findAll(pageable);
    }
    public Object updateStatus(OrderStatusRequest request){
        Contact contact = contactRepository.findById(request.getOrderId()).orElseThrow(()-> new NotFoundException("Không tìm thấy contact"));
        Contact.Status status;
        switch (request.getStatus()) {
            case "Đang sử dụng" -> status = Contact.Status.ACTIVE;
            case "Ngừng sử dụng" -> status = Contact.Status.BLOCK;
            default -> {
                return new BadRequestException("yêu cầu không đúng");
            }
        }
        contact.setStatus(status);
        contactRepository.save(contact);
        return contact;
    }
    public List<Contact> findContactByStatusValue(){
        return contactRepository.findByStatus(Contact.Status.ACTIVE);
    }
}
