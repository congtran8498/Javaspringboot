package com.example.demosecuritycustomeruser.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChangePassword {
    private String newPassword;
    private String token;
}
