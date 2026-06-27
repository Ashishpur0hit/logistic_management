package com.logistics.user_service.repository;

import com.logistics.user_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver , String> {

    @Query("""
       SELECT d
       FROM Driver d
       WHERE d.user.user_id = :userId
       """)
    Optional<Driver> findByUser_UserId(UUID userId);


    List<Driver> findByAvailability(Boolean availability);

    Optional<Driver> findFirstByAvailabilityTrue();
}
