package com.example.music.app.Exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>>  handleIllegalArgumentException(IllegalArgumentException exception){
        log.error("illegal argument",exception);
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error",exception.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.error("method argument not found",exception);
        Map<String,Object> error=new HashMap<>();
        error.put("timestamp",LocalDateTime.now());
        error.put("status",HttpStatus.BAD_REQUEST.value());

        Map<String,String> errorResponse=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorResponse.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        error.put("errors", errorResponse);

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEmtityNotFoundException(EntityNotFoundException exception){
        log.error("Entity not found",exception);
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error",exception.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Invalid request body or malformed JSON");

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUsernameNotFoundException(UsernameNotFoundException exception){
        log.error("Username not found",exception);
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
        errorResponse.put("error",exception.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error","Duplicate or invalid data.");

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>> handleRuntimeException(RuntimeException exception){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error",exception.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }



}

