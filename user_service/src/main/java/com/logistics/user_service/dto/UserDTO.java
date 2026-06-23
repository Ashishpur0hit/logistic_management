package com.logistics.user_service.dto;

import com.logistics.user_service.entity.Address;
import com.logistics.user_service.enums.AccountStatus;
import com.logistics.user_service.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private UUID user_id;

    @NotBlank(message = "FirstName can not be Blank !")
    private String firstname;
    private String lastname;

    @Email
    private String email;

    @NotNull
    private Role role;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank !")
    @Size(min = 6,max = 20,message = "Password length should be between 6 to 20 characters")
    private String password;

    @NotNull
    private AccountStatus status;


    private List<Address> addresses;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;
}
