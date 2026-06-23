package com.logistics.user_service.service;

import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.entity.Driver;
import com.logistics.user_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AdminService {

    Page<Driver> getAllDrivers(Integer page);
    Page<User> getAllUsers(Integer page);
    UserDTO markUserDisable(UUID userId);
    UserDTO activetUser(UUID userId);
    UserDTO getUser(UUID userId);
}
