package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.ColorDAO;
import vn.techmaster.demo.exception.BadRequestException;
import vn.techmaster.demo.modal.Color;

import java.util.List;
import java.util.Random;

@Service
public class ColorService {
    @Autowired
    private ColorDAO colorDAO;
    Random rd = new Random();
    public List<Color> all(){
        return colorDAO.findAllColor();
    }
    public Color randomColor1(){
        List<Color> colorNames = colorDAO.findAllColor();
        int randomIndex = rd.nextInt(colorNames.size());
        return colorNames.get(randomIndex);
    }
    public Color randomColor2(){
        Color color = new Color();
        String c = "#" + Integer.toHexString(rd.nextInt(16777215));
        color.setNameColor(c);
        return color;
    }
    public Color randomColor3(){
        int red = rd.nextInt(256);
        int green = rd.nextInt(256);
        int blue = rd.nextInt(256);
        Color color = new Color();
        String c = "rgb(" + red + ", " + green + ", " + blue + ")";
        color.setNameColor("rgb(" + red + ", " + green + ", " + blue + ")");
        return color;
    }
    public Color randomColor(String type){
        switch (type){
            case "1":
                return randomColor1();
            case "2":
                return randomColor2();
            case "3":
                return randomColor3();
            default:
                throw new BadRequestException("Type không hợp lệ");
        }
    }
}
