package com.logistics.payment_service.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentVerificationRequest {
    private String paymentId;
    private String orderId;
    private String signature;
}
