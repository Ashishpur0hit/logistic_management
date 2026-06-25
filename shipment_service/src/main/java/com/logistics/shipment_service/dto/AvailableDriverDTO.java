package com.logistics.shipment_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AvailableDriverDTO {
    private String firstname;
    private String lastname;
    private String driver_id;
}
