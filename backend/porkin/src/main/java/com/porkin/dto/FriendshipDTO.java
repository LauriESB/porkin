package com.porkin.dto;

import com.porkin.entity.FriendshipEntity;
import com.porkin.entity.PersonEntity;
import org.springframework.beans.BeanUtils;

public class FriendshipDTO {

  private Long idUser;

  private Long idFriend;

  private PersonEntity fkPersonUser;

  private PersonEntity fkPersonFriend;

  // FriendshipDTO constructors

  public FriendshipDTO(FriendshipEntity friendshipEntity) {
    BeanUtils.copyProperties(friendshipEntity, this);
  }

  public FriendshipDTO() {

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

  public PersonEntity getFkPersonUser() {
    return fkPersonUser;
  }

  public void setFkPersonUser(PersonEntity fkPersonUser) {
    this.fkPersonUser = fkPersonUser;
  }

  public PersonEntity getFkPersonFriend() {
    return fkPersonFriend;
  }

  public void setFkPersonFriend(PersonEntity fkPersonFriend) {
    this.fkPersonFriend = fkPersonFriend;
  }

}
