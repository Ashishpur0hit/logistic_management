package com.logistics.user_service.service;

import com.logistics.user_service.dto.LoginRequest;
import com.logistics.user_service.dto.UserDTO;

public interface AuthService {

    String login(LoginRequest request);

    UserDTO addUser(UserDTO userDTO);
}
