package com.example.usermanager.database;

import com.example.usermanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> userList = new ArrayList<>(
        List.of(
                new User(1,"Hương Thanh","huong@techmaster.vn", "0382416368","Ha Noi","https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag","12345"),
                new User(2,"Phạm Thị Mẫn","phamman@techmaster.vn", "0963023185","Ha Noi","https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0","02468"),
                new User(3,"Đức Thịnh","thinh@techmaster.vn", "0987273764","Ha Noi","https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/c3IwVOU2","13579")
        )
    );
}
