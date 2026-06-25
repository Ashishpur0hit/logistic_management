package com.logistics.user_service.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableDriverDTO {
    private String firstname;
    private String lastname;
    private String driver_id;
}
