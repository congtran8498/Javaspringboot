package vn.techmaster.demo.dao;

import vn.techmaster.demo.modal.Todo;

import java.util.List;

public interface TodoDAO {
    List<Todo> findAll();

    void save(Todo todo);

    void delete(Integer id);

    List<Todo> findByStatusContainsIgnoreCase(String status);
}
