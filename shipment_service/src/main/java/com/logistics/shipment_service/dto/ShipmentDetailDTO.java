package com.logistics.shipment_service.dto;

import com.logistics.shipment_service.entity.ShipmentStatusHistory;
import com.logistics.shipment_service.enums.ShipmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ShipmentDetailDTO {
    private Long shipment_id;
    private String trackingNumber;
    private String driverName;
    private Double latitude;
    private Double longitude;
    private String pickupAddress;
    private String dropAddress;
    private List<ShipmentStatusHistory> history;
    private BigDecimal shipmentValue;
    private Float shippingCost;
    private ShipmentStatus currentStatus;
}
