package com.porkin.porkin.repository;

import com.porkin.porkin.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
  UserDetails findByUsername(String username);

  Optional<PersonEntity> findByEmail(String email);

}
