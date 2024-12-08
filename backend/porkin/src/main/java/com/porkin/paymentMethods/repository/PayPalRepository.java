package com.porkin.paymentMethods.repository;

import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.entity.PayPalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayPalRepository extends JpaRepository<PayPalEntity, Long> {
    Optional<PayPalEntity> findByUsername(PersonEntity username);
}
