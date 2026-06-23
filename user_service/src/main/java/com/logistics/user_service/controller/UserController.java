package com.logistics.user_service.controller;

import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.dto.UserUpdateRequest;
import com.logistics.user_service.response.CustomApiResponse;
import com.logistics.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String healthCheck()
    {
        return "Hello Ashish";
    }


    // get User me
    @GetMapping("/get/{userId}")
    public ResponseEntity<CustomApiResponse<UserDTO>> fetchUser(@PathVariable UUID userId)
    {
        UserDTO fetchedUser = userService.getUser(userId);
        CustomApiResponse<UserDTO> response = CustomApiResponse.<UserDTO>builder()
                .success(true)
                .message("user fetched successfully !")
                .body(fetchedUser)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    // update User me
    @PutMapping("/get/{userId}")
    public ResponseEntity<CustomApiResponse<UserDTO>> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateRequest request)
    {
        UserDTO updatedUser = userService.updateUser(userId,request);
        CustomApiResponse<UserDTO> response = CustomApiResponse.<UserDTO>builder()
                .success(true)
                .message("user fetched successfully !")
                .body(updatedUser)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
