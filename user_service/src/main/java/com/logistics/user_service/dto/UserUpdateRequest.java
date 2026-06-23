package com.logistics.user_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    private String firstname;
    private String lastname;
    private String email;
}
