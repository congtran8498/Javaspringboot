package com.example.usermanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
