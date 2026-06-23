package com.logistics.user_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "address_generator"
    )
    private UUID address_id;

    private String country;
    private String state;
    private String city;
    @NotNull(message = "Address type can not be null !")
    @NotBlank(message = "Address type can not be blank !")
    private String type;

    @NotBlank(message = "Address can not be blank !")
    private String detailedAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
