package com.example.fastfood.service;

import com.example.fastfood.entity.Voucher;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.VoucherRepository;
import com.example.fastfood.request.UpsertVoucherRequest;
import com.example.fastfood.request.VoucherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    // lay danh sach voucher
    public List<Voucher> getAllVoucher(){
        return voucherRepository.findAll();
    }
    // tim voucher
    public Voucher findVoucherByCode(VoucherRequest request){
        Voucher voucher= voucherRepository.findByCode(request.getCode()).orElseThrow(()->new NotFoundException("không có voucher này"));
        if(voucher.getExpired_at().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Voucher đã hết hạn");
        }
        return voucher;
    }
    public Voucher findById(Long id){
        return voucherRepository.findById(id).orElseThrow(()-> new NotFoundException("Không có voucher"));
    }

    // tao voucher
    public Voucher createVoucher(UpsertVoucherRequest request){
        if(request.getDiscount() <=0 || request.getDiscount() > 100){
            throw new BadRequestException("giá trị lớn hơn bằng 0 và nhỏ hơn 100");
        }
        LocalDateTime date = LocalDateTime.of(formatterDate(request.getExpired_at()), LocalTime.MAX);
        if(date.isBefore(LocalDateTime.now())) throw new BadRequestException("Ngày hết hạn phải sau thời gian hiện tại");

        Voucher voucher = new Voucher();
        voucher.setDiscount(request.getDiscount());
        voucher.setCode(randomCode());
        voucher.setExpired_at(date);
        voucher.setStatus(Voucher.Status.ACTIVE);
        voucherRepository.save(voucher);
        return voucher;
    }
    public LocalDate formatterDate(String dateRequest){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateRequest, formatter);
    }

    public String randomCode(){
        int length = 12;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
       return sb.toString();
    }

    // cap nhat category
    public Voucher updateVoucher(Long id, UpsertVoucherRequest request){
        if(request.getDiscount() <=0 || request.getDiscount() > 100){
            throw new BadRequestException("giá trị lớn hơn bằng 0 và nhỏ hơn 100");
        }
        LocalDateTime date = LocalDateTime.of(formatterDate(request.getExpired_at()), LocalTime.MAX);
        if(date.isBefore(LocalDateTime.now())) throw new BadRequestException("Ngày hết hạn phải sau thời gian hiện tại");

        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        voucher.setDiscount(request.getDiscount());
        voucher.setExpired_at(date);
        voucherRepository.save(voucher);
        return voucher;
    }
//     xoa voucher
    public void deleteVoucher(Long id){
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("not found"));
        voucherRepository.delete(voucher);
    }

}
