package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.modal.Color;
import vn.techmaster.demo.service.ColorService;

import java.util.List;
import java.util.Random;
@RestController
@CrossOrigin
public class ColorController {
        @Autowired
        private ColorService colorService;
        @GetMapping("/allColor")
        public List<Color> allColor(){
                return colorService.all();
        }
        @GetMapping("/random-color")
        public ResponseEntity<?> rdColor(@RequestParam String type)  {
            return ResponseEntity.ok(colorService.randomColor(type));
        }
}
