package com.porkin.repository;

import com.porkin.entity.FriendRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Long> {
}
