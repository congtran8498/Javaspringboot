package com.example.blogdemo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserRequest {
    private String name;
    private String avatar;
    private List<Integer> roleIds;
}
