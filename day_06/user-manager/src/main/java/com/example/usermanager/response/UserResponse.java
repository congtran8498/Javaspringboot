package com.example.usermanager.response;

import com.example.usermanager.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private List<UserDto> data;
    private int currentPage;
    private int size;
    private int totalPage;
}
