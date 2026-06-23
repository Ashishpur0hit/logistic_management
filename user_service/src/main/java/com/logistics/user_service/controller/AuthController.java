package com.logistics.user_service.controller;

import com.logistics.user_service.dto.LoginRequest;
import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.response.CustomApiResponse;
import com.logistics.user_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<UserDTO>> registerUser(@Valid @RequestBody UserDTO userDTO)
    {
        UserDTO savedUser = authService.addUser(userDTO);
        CustomApiResponse<UserDTO> response = CustomApiResponse.<UserDTO>builder()
                .success(true)
                .message("User created successfully !!")
                .body(savedUser)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/login")
    public ResponseEntity<CustomApiResponse<String>> registerUser(@Valid @RequestBody LoginRequest request)
    {
        String token = authService.login(request);
        CustomApiResponse<String> response = CustomApiResponse.<String>builder()
                .success(true)
                .message("User LoggedIn successfully !!")
                .body(token)
                .build();
        return ResponseEntity.ok(response);
    }





}
