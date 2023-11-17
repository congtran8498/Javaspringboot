package com.example.fastfood.service;


import com.example.fastfood.entity.Size;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.SizeRepository;
import com.example.fastfood.request.UpsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;
    // lay danh sach size
    public List<Size> getAllSize(){
        return sizeRepository.findAll();
    }

    // tao size
    public Size createSize(UpsertRequest request){
        List<Size> sizeList = sizeRepository.findAll();
        for (Size value : sizeList) {
            if (value.getName().equalsIgnoreCase(request.getName().trim())) throw new BadRequestException("category da ton tai");
        }
        Size size = new Size();
        size.setName(request.getName());
        sizeRepository.save(size);
        return size;
    }

    // cap nhat size
    public Size updateSize

    (Long id, UpsertRequest request){
        List<Size> sizeList = sizeRepository.findAll();
        for (Size value : sizeList) {
            if (value.getName().equalsIgnoreCase(request.getName().trim())) throw new BadRequestException("category da ton tai");
        }

        Size size = sizeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        size.setName(request.getName());
        sizeRepository.save(size);
        return size;
    }
    // xoa size
    public void deleteSize(Long id){
        Size size = sizeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));

        sizeRepository.delete(size);
    }
}
