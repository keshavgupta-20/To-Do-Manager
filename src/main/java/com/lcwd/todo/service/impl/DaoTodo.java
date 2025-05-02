package com.lcwd.todo.service.impl;

import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import com.lcwd.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service

public class DaoTodo  implements TodoService {
    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.allTodos();
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoDao.getSingle(todoId);
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        return todoDao.updateTodo(id,todo);
    }

    @Override
    public void DeleteTodo(int id) {
        todoDao.Deletetodo(id);
    }
}
