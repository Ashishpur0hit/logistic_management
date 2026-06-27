package com.logistics.user_service.entity;

import com.logistics.user_service.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Driver {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private String driverId;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicle_type;

    @Column(unique = true)
    private String vehicle_no;
    @Column(unique = true)
    private String license_no;
    private Boolean availability;

    @Column(name = "current_latitude")
    private Double currentLatitude;

    @Column(name = "current_longitude")
    private Double currentLongitude;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
