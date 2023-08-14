package vn.techmaster.demo.controller;

import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.modal.Bmi;
import vn.techmaster.demo.modal.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController

public class WebController {
    private List<Bmi> bmiList;

    public WebController() {

        this.bmiList = new ArrayList<>();
        this.bmiList.add(new Bmi(10,20));
        this.bmiList.add(new Bmi(15,25));
    }
    //Bài 2: BMI

    @PostMapping("/bmi")
    public String createBmi(@RequestBody Bmi request) {
        Bmi bmi = new Bmi();
        try{
            valid(request.getHeight(),request.getWeight());
            bmi.setHeight(request.getHeight());
            bmi.setWeight(request.getWeight());
            this.bmiList.add(bmi);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
        return "Them thanh cong";
    }
    public void valid(int h, int w) throws IllegalArgumentException {
        if (h <= 0 || w <= 0) {
            throw new IllegalArgumentException("chỉ số phải > 0");
        }
    }

    @GetMapping("/bmi")
    public List<Bmi> searchBmi(@RequestParam String height, @RequestParam String weight){
        int h = Integer.parseInt(height);
        int w = Integer.parseInt(weight);
        return this.bmiList.stream()
                .filter(b -> b.getHeight().equals(h) && b.getWeight().equals(w))
                .toList();
    }


    //Bài 1: random color
    @GetMapping("/random-color")
    public String rdColor(@RequestParam String type)  {
        Color color = new Color();
        try{
            return getRandomColor(type);
        } catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
    public String getRandomColor(String type) throws IllegalArgumentException{
        Random random = new Random();

        switch (type) {
            case "1":
                // random color name
                String[] colorNames = {"black", "blue", "red", "green"};
                int randomIndex = random.nextInt(colorNames.length);
                return colorNames[randomIndex];

            case "2":
                // random HEX color
                return "#" + Integer.toHexString(random.nextInt(16777215));

            case "3":
                // random RGB color
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                return "rgb(" + red + ", " + green + ", " + blue + ")";
            default:
                throw new IllegalArgumentException("Type không hợp lệ");
        }
    }
}
