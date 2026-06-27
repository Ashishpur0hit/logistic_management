package com.logistics.common;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedisDriver {
    private String driverId;
    private String driverName;
    private Double latitude;
    private Double longitude;
}

