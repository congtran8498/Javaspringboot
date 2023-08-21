package vn.techmaster.demo.modal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    private Integer id;
    private String title;
    private Boolean status;
}
