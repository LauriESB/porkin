package com.porkin.compositekeys;

import java.io.Serializable;

public class FriendshipIDs implements Serializable {

  private Long idUser;
  private Long idFriend;

  public FriendshipIDs() {}

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
