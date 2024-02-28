package com.example.fastfood.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {
    @NotEmpty(message = "Trường này không được để trống")
    private String email;
    @NotEmpty(message = "Trường này không được để trống")
    @Size(min = 5, message = "mật khẩu tối thiếu 5 kí tự")
    private String password;
}
