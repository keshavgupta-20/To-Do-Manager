package com.lcwd.todo.service.impl;

import com.lcwd.todo.dao.TodoRepository;
import com.lcwd.todo.models.Todo;
import com.lcwd.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@Primary
public class TodoJpaService implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
       return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Something went worng"));
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        Todo todo1 =  todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Something went worng"));
        todo1.setTodoDate(todo.getTodoDate());
        todo1.setStatus(todo.getStatus());
        todo1.setTitle(todo.getTitle());
        todo1.setAddedDate(todo.getAddedDate());
        return todoRepository.save(todo1);


    }

    @Override
    public void DeleteTodo(int id) {
        Todo todo =  todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Something went worng"));
        todoRepository.delete(todo);
    }
}
