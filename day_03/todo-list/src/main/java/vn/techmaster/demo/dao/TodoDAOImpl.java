package vn.techmaster.demo.dao;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.TodoDB;
import vn.techmaster.demo.modal.Todo;

import java.util.List;

@Repository
public class TodoDAOImpl implements TodoDAO{

    @Override
    public List<Todo> findAll() {
        return TodoDB.todoList;
    }

    @Override
    public void save(Todo todo) {
        TodoDB.todoList.add(todo);
    }

    @Override
    public void delete(Integer id) {
        TodoDB.todoList.removeIf(todo -> todo.getId().equals(id));
    }

    @Override
    public List<Todo> findByStatusContainsIgnoreCase(String status) {
        return TodoDB.todoList.stream()
                .filter(todo -> Boolean.toString(todo.getStatus()).toLowerCase().equals(status.toLowerCase()))
                .toList();
    }
}
