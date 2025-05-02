package com.lcwd.todo.service.impl;

import com.lcwd.todo.Exception.ResourceNotFoundException;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService implements com.lcwd.todo.service.TodoService {
    Logger logger= LoggerFactory.getLogger(TodoService.class);
    List<Todo> todos = new ArrayList<>();


    public Todo createTodo(Todo todo){
        todos.add(todo);
        logger.info("todos {}", this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }
    public  Todo getTodo(int todoId){
        Todo t1 = todos.stream().filter(t -> todoId== t.getId()).findAny().orElseThrow(() -> new ResourceNotFoundException("Todo not found", HttpStatus.NOT_FOUND));
        logger.info("Todo : {}", t1);
        return t1;
    }

    public Todo updateTodo(int id, Todo todo) {
       List<Todo>  tods = todos.stream().map(t -> {
            if (t.getId() == id){
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return  t;
            } else{
                return t;
            }

        }).collect(Collectors.toList());

        todos = tods;
        todo.setId(id);
        return todo;

    }
    public void DeleteTodo(int id) {
        List<Todo> newList = todos.stream().filter(t -> id != t.getId()).collect(Collectors.toList());
        todos = newList;
    }
}
