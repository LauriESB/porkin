package com.porkin.dto;

import com.porkin.entity.FriendshipEntity;
import com.porkin.entity.PersonEntity;
import org.springframework.beans.BeanUtils;

public class FriendshipDTO {

  private Long idUser;

  private Long idFriend;

  // FriendshipDTO constructors

  public FriendshipDTO(FriendshipEntity friendshipEntity) {
    //BeanUtils.copyProperties(friendshipEntity, this);
    this.idUser = friendshipEntity.getFriendshipIDs().getIdUser();
    this.idFriend = friendshipEntity.getFriendshipIDs().getIdFriend();
  }

  public FriendshipDTO() {

  }

  public FriendshipDTO(Long idUser, Long idFriend) {
    this.idUser = idUser;
    this.idFriend = idFriend;
  }

  // getters and setters

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public Long getIdFriend() {
    return idFriend;
  }

  public void setIdFriend(Long idFriend) {
    this.idFriend = idFriend;
  }

}
