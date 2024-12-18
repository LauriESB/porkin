package com.porkin.porkin.paymentMethods.repository;

import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.paymentMethods.entity.PixEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixRepository extends JpaRepository<PixEntity, Long> {
  Optional<PixEntity> findByUsername(PersonEntity username);
}
