package com.porkin.repository;

import com.porkin.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long> {
}
