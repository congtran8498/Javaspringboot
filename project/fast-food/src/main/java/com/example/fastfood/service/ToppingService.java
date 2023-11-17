package com.example.fastfood.service;

import com.example.fastfood.entity.Size;
import com.example.fastfood.entity.Topping;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.ToppingRepository;
import com.example.fastfood.request.UpsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {
    @Autowired
    private ToppingRepository toppingRepository;

    // lay danh sach size
    public List<Topping> getAllTopping(){
        return toppingRepository.findAll();
    }

    // tao size
    public Topping createSize(UpsertRequest request){
        List<Topping> toppingList = toppingRepository.findAll();
        for (Topping value : toppingList) {
            if (value.getName().equalsIgnoreCase(request.getName().trim())) throw new BadRequestException("category da ton tai");
        }
        Topping topping = new Topping();
        topping.setName(request.getName());
        toppingRepository.save(topping);
        return topping;
    }

    // cap nhat size
    public Topping updateTopping(Long id, UpsertRequest request){
        List<Topping> toppingList = toppingRepository.findAll();
        for (Topping value : toppingList) {
            if (value.getName().equalsIgnoreCase(request.getName().trim())) throw new BadRequestException("category da ton tai");
        }

        Topping topping = toppingRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        topping.setName(request.getName());
        toppingRepository.save(topping);
        return topping;
    }
    // xoa size
    public void deleteTopping(Long id){
        Topping topping = toppingRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));

        toppingRepository.delete(topping);
    }
}
