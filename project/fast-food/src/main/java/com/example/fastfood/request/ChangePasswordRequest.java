package com.example.fastfood.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
//    @Size(min = 5,message = "mật khẩu tối thiểu 5 kí tự")
//    @NotEmpty(message = "thiếu mật khẩu cũ")
    private String oldPass;

//    @Size(min = 5, message = "mật khẩu tối thiểu 5 kí tự")
//    @NotEmpty(message = "thiếu mật khẩu mới")
    private String newPass;
}
