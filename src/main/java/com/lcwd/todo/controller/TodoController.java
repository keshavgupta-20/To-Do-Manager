package com.lcwd.todo.controller;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.service.impl.DaoTodo;
import com.lcwd.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    Random random = new Random();
    Logger logger = LoggerFactory.getLogger(TodoController.class);
    int id = random.nextInt(999999);

    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
        logger.info("Create Todo");
        int id = random.nextInt(999999);
        Date currentdate = new Date();
//        String str  = null;
//        logger.info("length {}", str.length());length
//        Integer.parseInt("1345djjd4");1345djjd4


        todo.setId(id);
        todo.setAddedDate(currentdate);

        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getallTodoHandler() {
        List<Todo> toods = todoService.getAllTodos();

        return new ResponseEntity<>(toods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getSingleTodo(@PathVariable int id) throws ParseException {
        Todo t2 = todoService.getTodo(id);
        return ResponseEntity.ok(t2);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWith, @PathVariable int id) {
        logger.info("updation  of {} ", id);
        Todo todo = todoService.updateTodo(id, todoWith);
        logger.info("update Done {} ", id);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Deletehandler(@PathVariable int id) {
        todoService.DeleteTodo(id);
        logger.info("Deleted of {} ", id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
    //defining Exception Handler
