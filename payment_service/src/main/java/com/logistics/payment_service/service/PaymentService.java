package com.logistics.payment_service.service;

import com.logistics.payment_service.request.PaymentVerificationRequest;
import com.razorpay.RazorpayException;

import java.math.BigDecimal;

public interface PaymentService {

     String createOrderId(BigDecimal amount , Long shipmentId) throws RazorpayException;

     String verifypayment(PaymentVerificationRequest request)throws RazorpayException;
}
