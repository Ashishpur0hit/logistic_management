package com.logistics.user_service.controller;

import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.entity.Driver;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.response.CustomApiResponse;
import com.logistics.user_service.service.AdminService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api/admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String adminHealth()
    {
        return "Admin is good";
    }

    //get all Driver
    @GetMapping("/get-all-drivers")
    public ResponseEntity<CustomApiResponse<Object>> getAllDrivers(@RequestParam Integer page)
    {
        Page<Driver> drivers = adminService.getAllDrivers(page);
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(true)
                .message("All drivers fetched successfully !")
                .body(drivers)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }


    //get all User
    @GetMapping("/get-all-users")
    public ResponseEntity<CustomApiResponse<Object>> getAllUsers(@RequestParam Integer page)
    {
        Page<User> users = adminService.getAllUsers(page);
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(true)
                .message("All users fetched successfully !")
                .body(users)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    // Disable User or Driver
    @PutMapping("/disable/{userId}")
    public ResponseEntity<CustomApiResponse<Object>> diableUser(@PathVariable UUID userId)
    {
        UserDTO user = adminService.markUserDisable(userId);
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(true)
                .message("User disabled successfully !")
                .body(user)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }


    @PutMapping("/activate/{userId}")
    public ResponseEntity<CustomApiResponse<Object>> activateUser(@PathVariable UUID userId)
    {
        UserDTO user = adminService.activetUser(userId);
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(true)
                .message("User activated successfully !")
                .body(user)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    //get User by id
    @GetMapping("/get/{userId}")
    public ResponseEntity<CustomApiResponse<Object>> getUser(@PathVariable UUID userId)
    {
        UserDTO user = adminService.getUser(userId);
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .success(true)
                .message("User fetched successfully !")
                .body(user)
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }



}
