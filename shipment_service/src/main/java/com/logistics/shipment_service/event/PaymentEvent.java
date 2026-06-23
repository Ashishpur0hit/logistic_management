package com.logistics.shipment_service.event;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaymentEvent {
    private Long shipmentId;
    private String razorpayOrderId;
    private String transactionId;
    private String status;
}
