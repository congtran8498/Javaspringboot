package com.example.fastfood.controller;

import com.example.fastfood.entity.Size;
import com.example.fastfood.entity.SizeTopping;
import com.example.fastfood.entity.Topping;
import com.example.fastfood.service.SizeService;
import com.example.fastfood.service.SizeToppingService;
import com.example.fastfood.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OptionProductController {
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ToppingService toppingService;
    @Autowired
    private SizeToppingService sizeToppingService;

    @GetMapping("/admin-page/size")
    public String getSize(Model model){
        List<Size> sizeList = sizeService.getAllSize();
        model.addAttribute("sizeList",sizeList);
        return "/admin/sizetopping/size";
    }

    @GetMapping("/admin-page/topping")
    public String getTopping(Model model){
        List<Topping> toppingList = toppingService.getAllTopping();
        model.addAttribute("toppingList",toppingList);
        return "/admin/sizetopping/topping";
    }

    @GetMapping("/admin-page/size-topping")
    public String getSizeTopping(Model model){
        List<SizeTopping> sizeToppingList = sizeToppingService.getAllSizeTopping();
        model.addAttribute("sizeToppingList",sizeToppingList);
        List<Topping> toppingList = toppingService.getAllTopping();
        model.addAttribute("toppingList",toppingList);
        List<Size> sizeList = sizeService.getAllSize();
        model.addAttribute("sizeList",sizeList);
        return "/admin/sizetopping/size-topping";
    }
}
