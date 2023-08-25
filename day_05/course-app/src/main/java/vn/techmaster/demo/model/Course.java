package vn.techmaster.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    Integer id;
    String name;
    String description;
    String type;
    List<String> topics;
    String thumbnail;
    Integer userId;
}
