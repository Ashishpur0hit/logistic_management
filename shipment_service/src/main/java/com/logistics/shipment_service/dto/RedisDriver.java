package com.logistics.shipment_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisDriver {
    private String driverId;
    private String driverName;
    private Double latitude;
    private Double longitude;
}
