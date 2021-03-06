package com.mycompany.filmbuff.util.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefinedException {

    private static final Logger logger = LoggerFactory.getLogger(DefinedException.class);
    //TODO: Remove exceptionMessage from ResponseEntity body and enter your own message. Also log the actual exception cause.

    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity<Object> exception(ConstraintViolationException exception) {
        StringBuilder exceptionMessage = new StringBuilder();
        for(ConstraintViolation<?> violation :exception.getConstraintViolations()){
            exceptionMessage.append(violation.getMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(exceptionMessage.toString());
    }

    @ExceptionHandler(value= FileNotFoundException.class)
    public ResponseEntity<Object> exception(FileNotFoundException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value= IOException.class)
    public ResponseEntity<Object> exception(IOException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value= SchedulerException.class)
    public ResponseEntity<Object> exception(SchedulerException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
