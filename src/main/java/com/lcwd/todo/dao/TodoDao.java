package com.lcwd.todo.dao;
import com.lcwd.todo.hepler.Helper;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TodoDao {
    private  JdbcTemplate template;
    public TodoDao(  @Autowired JdbcTemplate template){
        this.template = template;
        String createtable = "create table IF NOT EXISTS todos(id int primary key, title varchar(100) not null, content varchar(200), status varchar(15) not null, addedDate datetime, todoDate datetime)";
        int update =template.update(createtable);
        logger.info("Todo Table created {} ", update);
    }
    Logger logger  = LoggerFactory.getLogger(TodoDao.class);

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database
    public Todo saveTodo(Todo todo){
        String insertQuery  = "insert into todos(id, title,content, status, addedDate,todoDate) values(?,?,?,?,?,?)";
       int rows=  template.update(insertQuery, todo.getId(),todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getTodoDate());
        logger.info("Success {} inserted",rows);
        logger.info("JDBC Operation");
        return todo;
    }
    //get single todo
    public Todo getSingle(int id) throws ParseException {
        String query = "Select * from todos where id =  ?";
        Todo todo1 = template.queryForObject(query, new TodorowMapper(), id);
//       Map<String, Object> todo =  template.queryForMap(query, id);
//       logger.info("Todo :{}", todo);
//        Todo todo1  = new Todo();
//        todo1.setId((Integer) todo.get("id"));
//        todo1.setTitle((String) todo.get("title"));
//        todo1.setStatus((String) todo.get("content"));
//        todo1.setContent((String) todo.get("status"));
//        todo1.setTodoDate(Helper.parseDate((LocalDateTime)todo.get("todoDate")));
//        todo1.setAddedDate(Helper.parseDate((LocalDateTime)todo.get("addedDate")));

        return todo1;
    }
    //get all todo
    public List<Todo> allTodos(){
        String query = "Select * from todos";
       List<Map<String, Object>> maps  =template.queryForList(query);
      List<Todo>todos =  maps.stream().map((map) ->{
           Todo todo1  = new Todo();
           todo1.setId((Integer) map.get(("id")));
           todo1.setTitle((String) map.get("title"));
           todo1.setStatus((String) map.get("content"));
           todo1.setContent((String) map.get("status"));
          try {
              todo1.setTodoDate(Helper.parseDate((LocalDateTime)map.get("todoDate")));
              todo1.setAddedDate(Helper.parseDate((LocalDateTime)map.get("addedDate")));
          } catch (ParseException e) {
              throw new RuntimeException(e);
          }
           return todo1;
       } ).collect(Collectors.toList());
       return todos;
    }

    //update operaation
    public Todo updateTodo(int id, Todo todo){
        String query = "update todos set title = ?, content = ?, status = ?, addedDate = ?, todoDate = ? where id  = ?";
        int update = template.update(query,
                todo.getTitle(),
                todo.getContent(),
                todo.getStatus(),
                new java.sql.Timestamp(todo.getAddedDate().getTime()),
                new java.sql.Timestamp(todo.getTodoDate().getTime()),
                id
        );
        logger.info("Updated {} row(s)", update);
        todo.setId(id);
        return todo;
    }

    //delelte operation
    public  void Deletetodo(int id){
        //content
        String query = "delete from todos where id = ?";
        int update = template.update(query, id);
        logger.info("Deleted todos {}" , update);

    }
    //delete multiple
    public  void deletemultiple(int id[]){
        String query = "delete from todos where id = ?";
    int ints[] =template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int ids  =id[i];
                ps.setInt(1, ids);
            }

            @Override
            public int getBatchSize() {
                return id.length;
            }
        });
        for (int i : ints){
            logger.info("Deleted id {} " ,i);
        }
    }
}
