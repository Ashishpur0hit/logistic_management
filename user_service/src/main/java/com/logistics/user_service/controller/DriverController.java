package com.logistics.user_service.controller;

import com.logistics.user_service.dto.DriverDTO;
import com.logistics.user_service.response.CustomApiResponse;
import com.logistics.user_service.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/driver")
public class DriverController {

    @Autowired
    DriverService driverService;


    // Add driver

    @PostMapping("/add/{userId}")
    public ResponseEntity<CustomApiResponse<DriverDTO>> registerDriver(@PathVariable UUID userId , @Valid @RequestBody DriverDTO driverDTO)
    {
        DriverDTO savedDriver = driverService.addDriver(userId , driverDTO);
        CustomApiResponse<DriverDTO> response = CustomApiResponse.<DriverDTO>builder()
                .success(true)
                .message("Driver added successfully !")
                .body(savedDriver)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // get Driver by userId

    @GetMapping("/get/{userId}")
    public  ResponseEntity<CustomApiResponse<DriverDTO>> getDriver(@PathVariable UUID userId)
    {
        DriverDTO driver = driverService.getDriver(userId);
        CustomApiResponse<DriverDTO> response = CustomApiResponse.<DriverDTO>builder()
                .success(true)
                .message("Driver fetched successfully !")
                .body(driver)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    //update Availability
    @PutMapping("/update/{userId}")
    public  ResponseEntity<CustomApiResponse<DriverDTO>> updateDriverAvailability(@PathVariable UUID userId,@RequestParam Boolean availability)
    {
        DriverDTO driver = driverService.updateDriver(userId,availability);
        CustomApiResponse<DriverDTO> response = CustomApiResponse.<DriverDTO>builder()
                .success(true)
                .message("Driver updated successfully !")
                .body(driver)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
