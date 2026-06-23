package com.logistics.shipment_service.controller;

import com.logistics.shipment_service.entity.Shipment;
import com.logistics.shipment_service.response.CustomApiResponse;
import com.logistics.shipment_service.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/shipment")
public class ShipmentController {

    @Autowired
    ShipmentService shipmentService;


    @PostMapping("/add/{userId}")
    public ResponseEntity<CustomApiResponse<Shipment>> addShipment(@PathVariable UUID userId ,@Valid @RequestBody Shipment shipment)
    {
        Shipment savedShipment = shipmentService.createShipment(userId , shipment);
        CustomApiResponse<Shipment> response = CustomApiResponse.<Shipment>builder()
                .success(true)
                .body(savedShipment)
                .message("Shipment created successfully !")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
