package com.logistics.payment_service.handler;

import com.logistics.payment_service.response.CustomApiResponse;
import com.razorpay.RazorpayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloabalExceptionHandler {

    @ExceptionHandler(RazorpayException.class)
    public ResponseEntity<CustomApiResponse<Object>> razorPayExceptionHandler(RazorpayException ex)
    {
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(false)
                .body(ex.getLocalizedMessage())
                .message("RayzorPay exception Occured")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<Object>> genericExceptionHandler(Exception ex)
    {
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(false)
                .body(ex.getLocalizedMessage())
                .message("Unexpected exception Occured")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
