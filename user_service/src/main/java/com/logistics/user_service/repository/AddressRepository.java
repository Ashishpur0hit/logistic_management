package com.logistics.user_service.repository;

import com.logistics.user_service.entity.Address;
import com.logistics.user_service.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("""
       SELECT a
       FROM Address a
       WHERE a.user.user_id = :userId
       """)
    Page<Address> findByUser_UserId(UUID userId, Pageable pageable);
}
