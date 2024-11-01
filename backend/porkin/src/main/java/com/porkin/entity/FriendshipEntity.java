package com.porkin.entity;

import com.porkin.compositekeys.FriendshipIDs;
import com.porkin.dto.FriendshipDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "friendship")
public class FriendshipEntity {

  @EmbeddedId
  private FriendshipIDs friendshipIDs;

  @ManyToOne
  @MapsId("idUser")
  @JoinColumn(name = "idUser", referencedColumnName = "idUser")
  private PersonEntity fkPersonUser;

  @ManyToOne
  @MapsId("idFriend")
  @JoinColumn(name = "idFriend", referencedColumnName = "idUser")
  private PersonEntity fkPersonFriend;

  // pessoaEntity constructors

  public FriendshipEntity(FriendshipDTO friendshipDTO) {
    BeanUtils.copyProperties(friendshipDTO, this);
  }

  public FriendshipEntity() {

  }

  // getters and setters

  public Long getIdUser() {
    return friendshipIDs.getIdUser();
  }

  public void setIdUser(Long idUser) {
    friendshipIDs.setIdUser(idUser);
  }

  public Long getIdFriend() {
    return friendshipIDs.getIdFriend();
  }

  public void setIdFriend(Long idFriend) {
    friendshipIDs.setIdFriend(idFriend);
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

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FriendshipEntity that = (FriendshipEntity) o;
    return Objects.equals(friendshipIDs.getIdUser(), that.friendshipIDs.getIdUser());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(friendshipIDs.getIdUser());
  }

}
