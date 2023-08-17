package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.BmiDAO;
import vn.techmaster.demo.exception.BadRequestException;
import vn.techmaster.demo.modal.Bmi;

import java.text.DecimalFormat;

@Service
public class BmiService {
    @Autowired
    private BmiDAO bmiDAO;
    public Bmi chiSo(String height, String weight){
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        if(h <= 0 || w <=0){
           throw new BadRequestException("chỉ số phải lớn hơn 0");
        }
        double csBmi = w/(h*h);
        Bmi bmi = new Bmi();
        bmi.setChiSoBmi((double) Math.round(csBmi * 10) / 10);
        return bmi;
    }
}
