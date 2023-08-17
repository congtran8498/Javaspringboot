package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.service.BmiService;

@RestController
@CrossOrigin
public class BmiCotroller {
    @Autowired
    private BmiService bmiService;
    @GetMapping("/bmi")
    public ResponseEntity<?> chiSoBmi(@RequestParam String height, @RequestParam String weight){
        return ResponseEntity.ok(bmiService.chiSo(height,weight));
    }

}
