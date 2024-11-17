package com.porkin.repository;

import com.porkin.entity.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {
  //@Query("select a from FriendshipEntity a where a.friendshipIDs.idUser = :idUser")
  //List<FriendshipEntity> findByIdUser(@Param("idUser") Long idUser);
}
