package com.sulimann.springsecurityexample.exceptions.handler;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

import com.sulimann.springsecurityexample.configs.security.ForbiddenException;
import com.sulimann.springsecurityexample.exceptions.ResourceNotFoundException;
import com.sulimann.springsecurityexample.utils.constants.ErrorMessage;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO error = new ValidationErrorDTO(LocalDateTime.now(), status.value(), "Dados inválidos", request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(new FieldMessageDTO(f.getField(), f.getDefaultMessage()));
        }
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorDTO> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), ErrorMessage.ERRO_INTERNO, request.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<CustomErrorDTO> handleHttpStatusCodeException(HttpStatusCodeException e, HttpServletRequest request) {
        HttpStatusCode status = e.getStatusCode();
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), e.getResponseBodyAsString(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<CustomErrorDTO> handleResourceAccessException(ResourceAccessException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), "Serviço indisponível: " + e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

     @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomErrorDTO> forbiddenException(ForbiddenException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
