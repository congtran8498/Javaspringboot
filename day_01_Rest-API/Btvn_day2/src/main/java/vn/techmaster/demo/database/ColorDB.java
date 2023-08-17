package vn.techmaster.demo.database;

import vn.techmaster.demo.modal.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorDB {
    public static List<Color> postList = new ArrayList<>(
            List.of(
                    new Color("black"),
                    new Color("blue"),
                    new Color( "red"),
                    new Color("green")
            )
    );
}
