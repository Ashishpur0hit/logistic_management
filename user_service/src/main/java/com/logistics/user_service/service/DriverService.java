package com.logistics.user_service.service;

import com.logistics.common.RedisDriver;
import com.logistics.user_service.dto.AvailableDriverDTO;
import com.logistics.user_service.dto.DriverDTO;
import com.logistics.user_service.entity.Driver;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface DriverService {
    DriverDTO addDriver(UUID userId, DriverDTO driverDTO);

    DriverDTO getDriver(UUID userId);

    void updateDriver(String driverId , Boolean availiblity);

    AvailableDriverDTO getAvailableDriver();

    RedisDriver getDriverSnapshot(UUID driverId);
}
