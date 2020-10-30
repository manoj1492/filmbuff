package com.mycompany.filmbuff.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefinedException {
    
    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity<Object> exception(ConstraintViolationException exception) {
        StringBuilder exceptionMessage = new StringBuilder();
        for(ConstraintViolation<?> violation :exception.getConstraintViolations()){
            exceptionMessage.append(violation.getMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(exceptionMessage.toString());
    }
}
