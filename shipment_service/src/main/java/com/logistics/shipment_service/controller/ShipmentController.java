package com.logistics.shipment_service.controller;

import com.logistics.shipment_service.dto.ShipmentDetailDTO;
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

    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<CustomApiResponse<ShipmentDetailDTO>> addShipment(@PathVariable String trackingNumber)
    {
        ShipmentDetailDTO shipmentDetailDTO = shipmentService.trackShipment(trackingNumber);
        CustomApiResponse<ShipmentDetailDTO> response = CustomApiResponse.<ShipmentDetailDTO>builder()
                .success(true)
                .body(shipmentDetailDTO)
                .message("Shipment tracked successfully !")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PutMapping("/delivered/{shipmentId}")
    public ResponseEntity<CustomApiResponse<Shipment>> markShipmentDelivered(@PathVariable Long shipmentId)
    {
        Shipment shipment = shipmentService.markShipmentDelivered(shipmentId);
        CustomApiResponse<Shipment> response = CustomApiResponse.<Shipment>builder()
                .success(true)
                .body(shipment)
                .message("Shipment tracked successfully !")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
