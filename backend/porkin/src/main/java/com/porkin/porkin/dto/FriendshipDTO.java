package com.porkin.porkin.dto;

import com.porkin.porkin.entity.FriendshipEntity;

public class FriendshipDTO {

  private String idUser;

  private String idFriend;

  // FriendshipDTO constructors

  public FriendshipDTO(FriendshipEntity friendshipEntity) {
    //BeanUtils.copyProperties(friendshipEntity, this);
    this.idUser = friendshipEntity.getFkPersonUser().getUsername();
    this.idFriend = friendshipEntity.getFkPersonFriend().getUsername();
  }

  public FriendshipDTO(String idUser, String idFriend) {
    this.idUser = idUser;
    this.idFriend = idFriend;
  }

  public FriendshipDTO() {

  }

  // getters and setters

  public String getIdUser() {
    return idUser;
  }

  public void setIdUser(String idUser) {
    this.idUser = idUser;
  }

  public String getIdFriend() {
    return idFriend;
  }

  public void setIdFriend(String idFriend) {
    this.idFriend = idFriend;
  }

}
