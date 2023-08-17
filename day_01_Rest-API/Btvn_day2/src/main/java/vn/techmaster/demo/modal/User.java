package vn.techmaster.demo.modal;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private String email;
    private String passWord;
    private String avatar;
}
