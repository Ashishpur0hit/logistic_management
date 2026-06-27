package com.logistics.shipment_service.service;

import com.logistics.shipment_service.dto.ShipmentDetailDTO;
import com.logistics.shipment_service.entity.Shipment;
import com.logistics.shipment_service.event.PaymentEvent;

import java.util.UUID;

public interface ShipmentService {

    Shipment createShipment(UUID userId, Shipment shipment);

    void handlePaymentSuccess(PaymentEvent event);

    ShipmentDetailDTO trackShipment(String tackingNumber);

    Shipment markShipmentDelivered(Long shipmentId);
}
