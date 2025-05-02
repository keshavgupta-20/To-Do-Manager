package com.lcwd.todo;

import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);
	@Autowired
	private TodoDao todoDao;
	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Started");
		JdbcTemplate template = todoDao.getTemplate();
		logger.info("Template object {}", template);
		logger.info("Template object {}", template.getDataSource().getConnection().getSchema());

//		Todo todo=  new Todo();
//		todo.setId(1224);
//		todo.setTitle("Placememe");
//		todo.setStatus("Done");
//		todo.setTodoDate(new Date());
//		todo.setAddedDate(new Date());
//		todo.setContent("Wow");
//		todoDao.saveTodo(todo);

//		List<Todo> allTodos = todoDao.allTodos();
//		logger.info("ALl todos : {}", allTodos);

//		Todo todo = todoDao.getSingle(124);

//		124todo.setTitle("Placememetttic");
//		todo.setStatus("Done");
//		todo.setTodoDate(new Date());
//		todo.setAddedDate(new Date());
//		todo.setContent("Wow");
//		todoDao.saveTodo(todo);

//		todoDao.updateTodo(124, todo);
//		List<Todo> allTodos = todoDao.allTodos();
//	logger.info("ALl todos : {}", allTodos);
//	Todo todo1 = todoDao.getSingle(124);
//	logger.info("TODO : {} ", todo1);
//	todo1.setId(124);
//		todo1.setTitle("getting placement");
//		todo1.setContent("Wow");
//
//		todo1.setStatus("Doing");
//		todo1.setTodoDate(new Date());
//		todo1.setAddedDate(new Date());
//
//	todoDao.updateTodo(124, todo1);
//		todoDao.Deletetodo(125);
//		int ids[] = {123,124};
//		todoDao.deletemultiple(ids);

//		Todo todo = todoDao.getSingle(1224);
//		logger.info("Content {} ", todo);
	}
}
