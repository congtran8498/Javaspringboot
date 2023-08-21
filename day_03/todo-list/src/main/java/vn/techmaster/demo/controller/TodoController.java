package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.modal.Todo;
import vn.techmaster.demo.service.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class TodoController {
    @Autowired
    private TodoService todoService;

    //2. lay todo theo status
    @GetMapping("/todos")
    public ResponseEntity<?> searchTodo(@RequestParam(required = false) String status) {
        if(status != null){
            return ResponseEntity.ok(todoService.searchTodo(status));
        }
        return ResponseEntity.ok(todoService.getAllTodo());
    }

    //3. lay chi tiet todo theo id
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    //4. Tao moi todo
    @PostMapping("/todos")
    public ResponseEntity<?> createTodo( @RequestBody Todo request) {
        return new ResponseEntity<>(todoService.createTodo(request), HttpStatus.CREATED);
    }

    //5. update todo
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Integer id,  @RequestBody Todo request) {
        return ResponseEntity.ok().body(todoService.updateTodo(id,request));
    }

    //6. delete todo
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
