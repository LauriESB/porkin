package com.porkin.porkin.repository;

import com.porkin.porkin.entity.FriendRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Long> {
  Optional<FriendRequestEntity> findRequestByPersonReceiverAndPersonRequester(String personReceiver, String personRequester);
}
