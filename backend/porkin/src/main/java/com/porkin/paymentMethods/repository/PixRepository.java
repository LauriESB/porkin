package com.porkin.paymentMethods.repository;

import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.entity.PixEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixRepository extends JpaRepository<PixEntity, Long> {
  Optional<PixEntity> findByIdUser(PersonEntity person);
}
