package com.example.fastfood.service;

import com.example.fastfood.entity.SizeTopping;
import com.example.fastfood.entity.Topping;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.SizeRepository;
import com.example.fastfood.repository.SizeToppingRepository;
import com.example.fastfood.repository.ToppingRepository;
import com.example.fastfood.request.UpsertRequest;
import com.example.fastfood.request.dto.ToppingSizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeToppingService {
    @Autowired
    private SizeToppingRepository sizeToppingRepository;
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private SizeRepository sizeRepository;

    // lay danh sach sizeTopping
    public List<SizeTopping> getAllSizeTopping(){
        return sizeToppingRepository.findAll();
    }

    // tao SizeTopping
    public SizeTopping createSizeTopping(ToppingSizeDto request){

        SizeTopping sizeTopping = new SizeTopping();
        return getSizeTopping(request, sizeTopping);
    }

    private SizeTopping getSizeTopping(ToppingSizeDto request, SizeTopping sizeTopping) {
        sizeTopping.setTopping(toppingRepository.findById(request.getToppingId()).orElseThrow(()-> new NotFoundException("Không có topping này")));
        sizeTopping.setSize(sizeRepository.findById(request.getSizeId()).orElseThrow(()-> new NotFoundException("Không có size này")));
        sizeTopping.setPrice(request.getPrice());
        sizeToppingRepository.save(sizeTopping);

        return sizeTopping;
    }

    // cap nhat SizeTopping
    public SizeTopping updateSizeTopping(Long id, ToppingSizeDto request){
        SizeTopping sizeTopping = sizeToppingRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("không tìm thấy"));
        return getSizeTopping(request, sizeTopping);
    }
    // xoa SizeTopping
    public void deleteSizeTopping(Long id){
        SizeTopping sizeTopping = sizeToppingRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("không tìm thấy"));

        sizeToppingRepository.delete(sizeTopping);
    }

    // lay size Topping
    public List<SizeTopping> findBySizeId(Long id){
        return sizeToppingRepository.findBySize_Id(id);
    }
}
