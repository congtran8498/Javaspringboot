package vn.techmaster.demo.database;

import vn.techmaster.demo.modal.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> userList = new ArrayList<>(
            List.of(
                    new User(1,"Nguyen Van A", "email1","12345","avatar1"),
                    new User(2,"Nguyen Van B", "email2","12345","avatar2"),
                    new User(3,"Nguyen Van C", "email3","12345","avatar3")
            )
    );
}
