package com.logistics.shipment_service.listener;

import com.logistics.shipment_service.event.PaymentEvent;
import com.logistics.shipment_service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    @Autowired
    ShipmentService shipmentService;


    @KafkaListener(topics = {"payment-topic"} , groupId = "shipment-group")
    public  void consume(PaymentEvent event)
    {
        System.out.println("Event listend");
        shipmentService.handlePaymentSuccess(event);
    }
}
