package com.logistics.shipment_service.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomApiResponse<T> {
    private Boolean success;
    private T body;
    private String message;
}
