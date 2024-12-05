package com.porkin.repository;

import com.porkin.entity.NotificationsEntity;
import com.porkin.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long> {
  List<NotificationsEntity> findByPersonAndCreationDateAfter(PersonEntity person, LocalDateTime time);

  Optional<NotificationsEntity> findByPerson(PersonEntity person);
}
