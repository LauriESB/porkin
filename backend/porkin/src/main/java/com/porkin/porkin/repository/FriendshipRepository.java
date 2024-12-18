package com.porkin.porkin.repository;

import com.porkin.porkin.entity.FriendshipEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {

  @Modifying
  @Transactional
  @Query("DELETE FROM FriendshipEntity f WHERE (f.fkPersonUser.username = :user AND f.fkPersonFriend.username = :friend) OR (f.fkPersonUser.username = :friend AND f.fkPersonFriend.username = :user)")
  void deleteByUserAndFriend(@Param("user") String user, @Param("friend") String friend);

}
