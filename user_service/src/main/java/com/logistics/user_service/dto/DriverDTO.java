package com.logistics.user_service.dto;

import com.logistics.user_service.enums.VehicleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DriverDTO {

    private UUID driverId;


    @NotNull(message = "Vehicle type can not be null !")
    private VehicleType vehicle_type;

    @NotNull(message = "Vehicle number can not be null !")
    @NotBlank(message = "Vehicle number can not be blank !")
    private String vehicle_no;

    @NotNull(message = "License number can not be null !")
    @NotBlank(message = "License number can not be blank !")
    private String license_no;


    private Double currentLatitude;


    private Double currentLongitude;

}
