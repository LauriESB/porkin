/*package com.porkin.compositekeys;


import java.io.Serializable;
import java.util.Objects;

public class FriendshipIDs implements Serializable {

  private String idUser;
  private String idFriend;

  // constructors

  public FriendshipIDs(String idUser, String idFriend) {
    this.idUser = idUser;
    this.idFriend = idFriend;
  }

  public FriendshipIDs() {}

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
*/