package com.logistics.shipment_service.external;

import com.logistics.common.RedisDriver;
import com.logistics.shipment_service.dto.AvailableDriverDTO;
import com.logistics.shipment_service.response.CustomApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "user-service",path = "/v1/api/internal")
public interface UserService {

    @GetMapping("/get-available-driver")
    public CustomApiResponse<AvailableDriverDTO> getAvailableDriver();

    @GetMapping("/get/driver/{driverId}")
    public CustomApiResponse<RedisDriver> getDriverSnapshot(@PathVariable String driverId);


    @PutMapping("/update/{driverId}")
    public CustomApiResponse<Object> updateDriverAvailability(@PathVariable UUID driverId, @RequestParam Boolean availability);

}
