package com.example.blogdemo.model.projection;

import com.example.blogdemo.entity.Role;


import java.util.List;
public interface UserInfo {
    Integer getId();
    String getName();
    String getEmail();
    String getAvatar();

    List<RoleInfo> getRoleList();
    interface RoleInfo{
        Integer getId();
        String getName();
    }
}
