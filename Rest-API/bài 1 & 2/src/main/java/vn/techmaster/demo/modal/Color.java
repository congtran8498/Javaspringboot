package vn.techmaster.demo.modal;

import lombok.*;

import java.io.IOException;
import java.util.Random;

@Getter
@Setter
@ToString

public class Color {

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
