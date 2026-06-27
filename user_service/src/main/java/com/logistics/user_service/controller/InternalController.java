package com.logistics.user_service.controller;

import com.logistics.common.RedisDriver;
import com.logistics.user_service.dto.AvailableDriverDTO;
import com.logistics.user_service.dto.DriverDTO;
import com.logistics.user_service.entity.Driver;
import com.logistics.user_service.response.CustomApiResponse;
import com.logistics.user_service.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api/internal")
public class InternalController {
    @Autowired
    DriverService driverService;


    @GetMapping("/get-available-driver")
    public ResponseEntity<CustomApiResponse<AvailableDriverDTO>> getAvailableDriver()
    {
        AvailableDriverDTO driver = driverService.getAvailableDriver();
        CustomApiResponse<AvailableDriverDTO> response = CustomApiResponse.<AvailableDriverDTO>builder()
                .body(driver)
                .success(true)
                .message("Driver fetched !")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/driver/{driverId}")
    public ResponseEntity<CustomApiResponse<RedisDriver>> getDriverSnapshot(@PathVariable UUID driverId)
    {
        RedisDriver driver = driverService.getDriverSnapshot(driverId);
        CustomApiResponse<RedisDriver> response = CustomApiResponse.<RedisDriver>builder()
                .body(driver)
                .success(true)
                .message("Driver fetched !")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //update Availability
    @PutMapping("/update/{driverId}")
    public  ResponseEntity<CustomApiResponse<Object>> updateDriverAvailability(@PathVariable UUID driverId, @RequestParam Boolean availability)
    {
        driverService.updateDriver(String.valueOf(driverId),availability);
        CustomApiResponse<Object> response = CustomApiResponse.<Object>builder()
                .success(true)
                .message("Driver updated successfully !")
                .body(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
