package com.logistics.user_service.service;

import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.dto.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface UserService {

    UserDTO getUser(UUID userId);

    UserDTO updateUser(UUID userId , UserUpdateRequest request);

}
