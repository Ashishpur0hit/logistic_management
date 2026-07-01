package com.logistics.shipment_service.handler;

import com.logistics.shipment_service.response.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomApiResponse<Object>> runtimeExceptionHandler(RuntimeException ex)
    {
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(false)
                .body(ex.getLocalizedMessage())
                .message("Exception occured")
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
