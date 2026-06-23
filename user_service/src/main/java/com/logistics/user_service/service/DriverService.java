package com.logistics.user_service.service;

import com.logistics.user_service.dto.DriverDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface DriverService {
    DriverDTO addDriver(UUID userId, DriverDTO driverDTO);

    DriverDTO getDriver(UUID userId);

    DriverDTO updateDriver(UUID userId , Boolean availiblity);
}
