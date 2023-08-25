package vn.techmaster.demo.dao;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.UserDB;
import vn.techmaster.demo.model.User;

import java.util.List;
import java.util.Optional;
@Repository
public class UserDAOImpl implements UserDAO{
    @Override
    public List<User> findAll() {
        return UserDB.userList;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return UserDB.userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
