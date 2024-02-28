package com.example.fastfood.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfor {
    private String name;
    private List<Long> roleIds;
    private Boolean isEnabled;
}
