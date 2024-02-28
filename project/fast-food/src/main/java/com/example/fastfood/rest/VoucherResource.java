package com.example.fastfood.rest;

import com.example.fastfood.request.UpsertVoucherRequest;
import com.example.fastfood.request.VoucherRequest;
import com.example.fastfood.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/voucher")
public class VoucherResource {
    @Autowired
    private VoucherService voucherService;

    @PostMapping
    public ResponseEntity<?> createVoucher(@RequestBody UpsertVoucherRequest request){
        return new ResponseEntity<>(voucherService.createVoucher(request), HttpStatus.CREATED);
    }
    @PostMapping("/find")
    public ResponseEntity<?> findVoucher(@RequestBody VoucherRequest request){
        return ResponseEntity.ok(voucherService.findVoucherByCode(request));
    }

    //Cap nhat
    @PutMapping("{id}")
    public ResponseEntity<?> updateVoucher(@PathVariable Long id, @RequestBody UpsertVoucherRequest request){
        return ResponseEntity.ok(voucherService.updateVoucher(id,request));
    }


    //Xoa
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable Long id){
        voucherService.deleteVoucher(id);
        return ResponseEntity.noContent().build();
    }
}
