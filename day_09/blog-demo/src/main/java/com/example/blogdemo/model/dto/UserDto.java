package com.example.blogdemo.model.dto;

import com.example.blogdemo.entity.Role;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String avatar;
    private List<RoleDto> roles;
}
