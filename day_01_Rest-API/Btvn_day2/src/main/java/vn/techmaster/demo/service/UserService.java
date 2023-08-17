package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.UserDAO;
import vn.techmaster.demo.exception.BadRequestException;
import vn.techmaster.demo.modal.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public User findUser(String userName, String passWord){
        List<User> userList = userDAO.findAllUser();
        return userList.stream()
                .filter(u -> u.getUserName().equals(userName) && u.getPassWord().equals(passWord))
                .findFirst()
                .orElseThrow(() -> {
                    throw new BadRequestException("Not found user");
                });
    }

    public List<User> all() {
        return userDAO.findAllUser();
    }
}
