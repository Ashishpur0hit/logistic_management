package com.logistics.payment_service.event;

import com.logistics.payment_service.enums.PaymentStatus;
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
