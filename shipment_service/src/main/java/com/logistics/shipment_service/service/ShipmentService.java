package com.logistics.shipment_service.service;

import com.logistics.shipment_service.entity.Shipment;

import java.util.UUID;

public interface ShipmentService {

    Shipment createShipment(UUID userId, Shipment shipment);
}
