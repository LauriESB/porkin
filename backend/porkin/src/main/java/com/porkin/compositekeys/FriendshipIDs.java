package com.porkin.compositekeys;

import java.io.Serializable;
import java.util.Objects;

public class FriendshipIDs implements Serializable {

  private Long idUser;
  private Long idFriend;

  public FriendshipIDs() {}

  public FriendshipIDs(Long idUser, Long idFriend) {
    this.idUser = idUser;
    this.idFriend = idFriend;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FriendshipIDs that = (FriendshipIDs) o;
    return Objects.equals(idUser, that.idUser) && Objects.equals(idFriend, that.idFriend);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUser, idFriend);
  }

}
