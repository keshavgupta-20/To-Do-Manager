package com.lcwd.todo.Exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //we have to create handler method for specific exception
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex){
        logger.warn(ex.getMessage());
        logger.info("Its a null pointer Exception form Global Handler");
        return new ResponseEntity<>("You have entered zero which can't possible", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberPointerException(NumberFormatException ex){
        logger.warn(ex.getMessage());
        logger.info("Its a number format exception pointer Exception form Global Handler");
        return new ResponseEntity<>("You have entered somethings wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNullPointerExceptionss(Exception ex){
        logger.warn(ex.getMessage());
        logger.info("It's  Exception form Global Handler");
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        logger.error("ERROR {} ",ex.getMessage());
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccess(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}



