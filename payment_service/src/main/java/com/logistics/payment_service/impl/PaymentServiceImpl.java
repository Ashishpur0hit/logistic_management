package com.logistics.payment_service.impl;

import com.logistics.payment_service.entity.Payment;
import com.logistics.payment_service.enums.PaymentStatus;
import com.logistics.payment_service.event.PaymentEvent;
import com.logistics.payment_service.repository.PaymentRepository;
import com.logistics.payment_service.request.PaymentVerificationRequest;
import com.logistics.payment_service.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static okio.HashingSource.hmacSha256;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    RazorpayClient razorpayClient;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private  KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    // pehle razor pay kisi bhi transaction k pehle ek order Id generate karta hai
    // wo id hum front end m bhej k hateway open karnege
    // gateway se transaction hone k baad jo transaction ka signature hoga  wo wapis yahan verify hone k baad databse pe write hoga


    @Override
    public String createOrderId(BigDecimal amount,Long shipmentId) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount" , amount.multiply(BigDecimal.valueOf(100)));
        options.put("currency","INR");
        options.put("receipt","receipt_"+System.currentTimeMillis());

        Order order = razorpayClient.orders.create(options);


        Payment payment = Payment.builder()
                .amount(amount)
                .razorpayOrderId(order.get("id"))
                .status(PaymentStatus.PAYMENT_PENDING)
                .shipmentId(shipmentId)
                .build();

        paymentRepository.save(payment);


        return order.toString();
    }

    @Override
    public String verifypayment(PaymentVerificationRequest request) throws RazorpayException {
        JSONObject attributes =
                new JSONObject();

        attributes.put(
                "razorpay_order_id",
                request.getOrderId()
        );

        attributes.put(
                "razorpay_payment_id",
                request.getPaymentId()
        );

        attributes.put(
                "razorpay_signature",
                request.getSignature()
        );

        boolean result =
                Utils.verifyPaymentSignature(
                        attributes,
                        keySecret
                );

        Payment payment = paymentRepository.findByRazorpayOrderId(request.getOrderId()).orElseThrow(()-> new RuntimeException("Razorpay orderId is wrong"));
        payment.setTransactionId(request.getPaymentId());

        if(result) payment.setStatus(PaymentStatus.PAYMENT_SUCCESS);
        paymentRepository.save(payment);

        PaymentEvent event = PaymentEvent.builder()
                .razorpayOrderId(payment.getRazorpayOrderId())
                .transactionId(payment.getTransactionId())
                .shipmentId(payment.getShipmentId())
                .status(payment.getStatus().toString())
                .build();

        kafkaTemplate.send("payment-topic",event);


        return (result)?"Payment Successfull":"Payment could not be verified";

    }
}
