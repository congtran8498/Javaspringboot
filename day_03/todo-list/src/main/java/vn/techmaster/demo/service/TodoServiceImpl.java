package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.TodoDAO;
import vn.techmaster.demo.exception.ResouceNotFoundException;
import vn.techmaster.demo.modal.Todo;

import java.util.List;
import java.util.Random;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoDAO todoDAO;

    @Override
    public List<Todo> getAllTodo() {
        return todoDAO.findAll();
    }

    @Override
    public Todo getTodoById(Integer id) {
        List<Todo> postList = todoDAO.findAll();
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResouceNotFoundException("Not found post"));
    }

    @Override
    public Todo createTodo(Todo request) {
        List<Todo> list = todoDAO.findAll();
        int id = list.get(list.size()-1).getId() + 1;
        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle(request.getTitle());
        todo.setStatus(false);
        todoDAO.save(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(Integer id, Todo request) {
        Todo todo = getTodoById(id);
        todo.setTitle(request.getTitle());
        todo.setStatus(request.getStatus());
        return todo;
    }

    @Override
    public void deleteTodo(Integer id) {
        todoDAO.delete(id);
    }

    @Override
    public List<Todo> searchTodo(String status) {
        return todoDAO.findByStatusContainsIgnoreCase(status);
    }
}
