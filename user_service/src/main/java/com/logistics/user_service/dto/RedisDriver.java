package com.logistics.user_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RedisDriver {
    private String driverId;
    private String driverName;
    private Double latitude;
    private Double longitude;
}
