package com.logistics.user_service.handler;

import com.logistics.user_service.exception.EmailAlreadyExistException;
import com.logistics.user_service.exception.ResourceNotFoundException;
import com.logistics.user_service.response.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<CustomApiResponse<Object>> emailAlreadyExistExceptionHandler(EmailAlreadyExistException ex)
    {
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .body(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomApiResponse<Object>> resourceNotFoundExceptionnHandler(ResourceNotFoundException ex)
    {
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .body(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomApiResponse<Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(false)
                .message(message)
                .body(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
