package com.logistics.user_service.entity;

import com.logistics.user_service.enums.AccountStatus;
import com.logistics.user_service.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID user_id;
    private String firstname;
    private String lastname;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;
    private String password;

    @OneToMany(
            cascade = CascadeType.ALL,mappedBy = "user"
    )

    private List<Address> addresses;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
