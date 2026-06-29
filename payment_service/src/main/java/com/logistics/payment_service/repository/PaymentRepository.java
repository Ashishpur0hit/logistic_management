package com.logistics.payment_service.repository;

import com.logistics.payment_service.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Transactional
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);


}
