package vn.techmaster.demo.service;

import vn.techmaster.demo.modal.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodo();

    Todo getTodoById(Integer id);

    Todo createTodo(Todo request);

    Todo updateTodo(Integer id, Todo request);

    void deleteTodo(Integer id);
    List<Todo> searchTodo(String status);
}
