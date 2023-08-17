package vn.techmaster.demo.dao;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.ColorDB;
import vn.techmaster.demo.modal.Color;

import java.util.List;

@Repository
public class ColorDAO {
    public List<Color> findAllColor() {
        return ColorDB.postList;
    }
}
