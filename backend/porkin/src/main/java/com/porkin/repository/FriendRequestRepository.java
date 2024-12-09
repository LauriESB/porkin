package com.porkin.repository;

import com.porkin.entity.FriendRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Long> {
  Optional<FriendRequestEntity> findByPersonReceiverAndPersonRequester(@Param("receiver") String receiver, @Param("requester") String requester);
}
