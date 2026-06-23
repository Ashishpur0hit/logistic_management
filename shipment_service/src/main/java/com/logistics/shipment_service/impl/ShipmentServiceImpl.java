package com.logistics.shipment_service.impl;

import com.logistics.shipment_service.entity.Shipment;
import com.logistics.shipment_service.entity.ShipmentStatusHistory;
import com.logistics.shipment_service.enums.ShipmentStatus;
import com.logistics.shipment_service.repository.ShipmentRepository;
import com.logistics.shipment_service.repository.ShipmentStatusHistoryRepository;
import com.logistics.shipment_service.service.ShipmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final Float baseFee=100F;
    private final Float weightCharge=1.5F;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;

    @Override
    @Transactional
    public Shipment createShipment(UUID userId , Shipment shipment) {
        shipment.setTrackingNumber(UUID.randomUUID().toString());
        shipment.setShippingCost(baseFee + weightCharge * shipment.getWeight());
        shipment.setUser_id(userId);
        shipment.setStatus(ShipmentStatus.CREATED);

        Shipment savedShipment = shipmentRepository.save(shipment);

        ShipmentStatusHistory history = ShipmentStatusHistory.builder()
                .shipment(savedShipment)
                .remark(" Shipment created")
                .status(ShipmentStatus.CREATED)
                .updatedBy("SYSTEM")
                .build();

        shipmentStatusHistoryRepository.save(history);
        return savedShipment;
    }
}
