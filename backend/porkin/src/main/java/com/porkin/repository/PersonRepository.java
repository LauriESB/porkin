package com.porkin.repository;

import com.porkin.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
  Optional<PersonEntity> findByUsername(String username);
}
