package vn.techmaster.demo.dao;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.UserDB;
import vn.techmaster.demo.modal.User;

import java.util.List;

@Repository
public class UserDAO {
    public List<User>  findAllUser(){
        return UserDB.userList;
    }
}
