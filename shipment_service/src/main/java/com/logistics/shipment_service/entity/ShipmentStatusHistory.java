package com.logistics.shipment_service.entity;

import com.logistics.shipment_service.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipment_status_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShipmentStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipment_status_id;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @Enumerated
    private ShipmentStatus status;

    private String remark;

    private String updatedBy;

    @CreationTimestamp
    private LocalDateTime timestamp;

}
