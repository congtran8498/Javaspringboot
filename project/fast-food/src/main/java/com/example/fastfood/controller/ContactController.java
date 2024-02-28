package com.example.fastfood.controller;

import com.example.fastfood.entity.Contact;
import com.example.fastfood.entity.Order;
import com.example.fastfood.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @GetMapping("/admin-page/contact")
    public String getContactPage(
            Model model,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<Contact> pageData = contactService.findAll(page,pageSize);
        List<String> statusList = Arrays.stream(Contact.Status.values())
                .map(Contact.Status::getValue)
                .collect(Collectors.toList());
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage", page);
        model.addAttribute("statusList",statusList);
        return "admin/contact/index";
    }
}
