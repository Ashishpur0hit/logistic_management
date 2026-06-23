package com.logistics.shipment_service.entity;

import com.logistics.shipment_service.enums.ShipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipment_id;

    private String trackingNumber;

    private UUID user_id;


    @NotBlank(message = "Pickup address can not be blank !")
    private String pickupAddress;


    @NotBlank(message = "Destination address can not be blank !")
    private String dropAddress;


    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be greater than 0")
    private Float weight;


    @NotNull(message = "Length is required")
    @Positive(message = "Length must be greater than 0")
    private BigDecimal length;


    @NotNull(message = "Width is required")
    @Positive(message = "Width must be greater than 0")
    private BigDecimal width;

    @NotNull(message = "Height is required")
    @Positive(message = "Height must be greater than 0")
    private BigDecimal height;


    @NotNull(message = "Shipment value can not be blank !")
    private BigDecimal shipmentValue;

    private Float shippingCost;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
