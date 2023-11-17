package com.example.fastfood.controller;

import com.example.fastfood.entity.Category;
import com.example.fastfood.entity.Voucher;
import com.example.fastfood.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/admin-page/voucher")
    public String getCategories(Model model){
        List<Voucher> voucherList = voucherService.getAllVoucher();
        model.addAttribute("voucherList",voucherList);
        return "admin/voucher/index";
    }
}
