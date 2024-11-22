package com.porkin.paymentMethods.repository;

import com.porkin.paymentMethods.entity.PayPalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayPalRepository extends JpaRepository<PayPalEntity, Long> {
}
