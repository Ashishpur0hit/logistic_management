package com.logistics.payment_service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomApiResponse <T>{
    private Boolean success;
    private T body;
    private String message;
}
