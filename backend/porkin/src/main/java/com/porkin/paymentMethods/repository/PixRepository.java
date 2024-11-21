package com.porkin.paymentMethods.repository;

import com.porkin.paymentMethods.entity.PixEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<PixEntity, Long> {
}
