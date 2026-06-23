package com.logistics.payment_service.controller;

import com.logistics.payment_service.request.PaymentVerificationRequest;
import com.logistics.payment_service.response.CustomApiResponse;
import com.logistics.payment_service.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/create/{shipmentId}")
    public ResponseEntity<CustomApiResponse<String>> createPayment(@RequestParam Integer amount , @PathVariable Long shipmentId) throws RazorpayException
    {
        String orderInfo = paymentService.createOrderId(BigDecimal.valueOf(amount), shipmentId);
        CustomApiResponse<String> response = CustomApiResponse.<String>builder()
                .success(true)
                .message("OrderId generated !")
                .body(orderInfo)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/verify")
    public ResponseEntity<CustomApiResponse<String>> verifyPayment(@RequestBody PaymentVerificationRequest request) throws RazorpayException
    {
        String paymentInfo = paymentService.verifypayment(request);
        CustomApiResponse<String> response = CustomApiResponse.<String>builder()
                .success(true)
                .message("Payment verification Done !")
                .body(paymentInfo)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
