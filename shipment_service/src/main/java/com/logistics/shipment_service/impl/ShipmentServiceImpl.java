package com.logistics.shipment_service.impl;

import com.logistics.common.RedisDriver;
import com.logistics.shipment_service.dto.AvailableDriverDTO;

import com.logistics.shipment_service.dto.ShipmentDetailDTO;
import com.logistics.shipment_service.entity.Shipment;
import com.logistics.shipment_service.entity.ShipmentStatusHistory;
import com.logistics.shipment_service.enums.ShipmentStatus;
import com.logistics.shipment_service.event.PaymentEvent;
import com.logistics.shipment_service.external.UserService;
import com.logistics.shipment_service.repository.ShipmentRepository;
import com.logistics.shipment_service.repository.ShipmentStatusHistoryRepository;
import com.logistics.shipment_service.response.CustomApiResponse;
import com.logistics.shipment_service.service.ShipmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final Float baseFee=100F;
    private final Float weightCharge=1.5F;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;


    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;








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

    @Override
    public void handlePaymentSuccess(PaymentEvent event) {

        System.out.println("handling");
        Shipment shipment = shipmentRepository.findById(event.getShipmentId()).orElseThrow(()-> new RuntimeException("Shipment not found !"));
        if(event.getStatus().equals("PAYMENT_SUCCESS"))
        {
            shipment.setStatus(ShipmentStatus.PAYMENT_SUCCESS);
        }
        Shipment savedShipment = shipmentRepository.save(shipment);

        ShipmentStatusHistory addhistory = ShipmentStatusHistory.builder()
                .updatedBy("SYSTEM")
                .status(savedShipment.getStatus())
                .shipment(savedShipment)
                .build();

        if(savedShipment.getStatus().equals(ShipmentStatus.PAYMENT_SUCCESS)) addhistory.setRemark("Payment verified !");
        else addhistory.setRemark("Payment not verified or pending !");

        shipmentStatusHistoryRepository.save(addhistory);

        System.out.println("handled");

        assignDriver(event.getShipmentId());

    }

    @Override
    public ShipmentDetailDTO trackShipment(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber).orElseThrow(()-> new RuntimeException("Shipment not found !!"));
        List<ShipmentStatusHistory> historyList = shipmentStatusHistoryRepository.findHistory(shipment.getShipment_id());
        ShipmentDetailDTO shipmentDetail = ShipmentDetailDTO.builder()
                .shipment_id(shipment.getShipment_id())
                .currentStatus(shipment.getStatus())
                .shipmentValue(shipment.getShipmentValue())
                .history(historyList)
                .dropAddress(shipment.getDropAddress())
                .pickupAddress(shipment.getPickupAddress())
                .shippingCost(shipment.getShippingCost())
                .trackingNumber(shipment.getTrackingNumber())
                .build();

        RedisDriver driver = (RedisDriver) redisTemplate.opsForValue().get("driver:location:"+shipment.getDriver_id());
        if(driver!=null)
        {
            shipmentDetail.setLatitude(driver.getLatitude());
            shipmentDetail.setLongitude(driver.getLongitude());
            shipmentDetail.setDriverName(driver.getDriverName());
            return shipmentDetail;
        }

        driver = userService.getDriverSnapshot(String.valueOf(shipment.getDriver_id())).getBody();

        shipmentDetail.setLatitude(driver.getLatitude());
        shipmentDetail.setLongitude(driver.getLongitude());
        shipmentDetail.setDriverName(driver.getDriverName());



        return shipmentDetail;
    }

    private void assignDriver(Long shipmentId) {

        CustomApiResponse<AvailableDriverDTO> response = userService.getAvailableDriver();
        if (response.getSuccess())
        {
            AvailableDriverDTO driver = response.getBody();
            Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(()-> new RuntimeException("Shipment not found !"));
            shipment.setDriver_id(UUID.fromString(driver.getDriver_id()));
            shipment.setStatus(ShipmentStatus.ASSIGNED);
            Shipment updatedShipment  = shipmentRepository.save(shipment);

            ShipmentStatusHistory addhistory = ShipmentStatusHistory.builder()
                    .updatedBy("SYSTEM")
                    .status(updatedShipment.getStatus())
                    .shipment(updatedShipment)
                    .remark("Driver assigned to your Shipment")
                    .build();

            shipmentStatusHistoryRepository.save(addhistory);

            // make a kafka event here publish driver{firstname , Lastname , DriverId} to DriverAssignedTopic which can be consumed by notification service in future
        }
        return;

    }
}
