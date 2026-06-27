package com.logistics.shipment_service.repository;

import com.logistics.shipment_service.entity.ShipmentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentStatusHistoryRepository extends JpaRepository<ShipmentStatusHistory,Long> {
    @Query("""
SELECT s
FROM ShipmentStatusHistory s
WHERE s.shipment.shipment_id = :shipmentId
ORDER BY s.timestamp ASC
""")
    List<ShipmentStatusHistory> findHistory(@Param("shipmentId") Long shipmentId);
}
