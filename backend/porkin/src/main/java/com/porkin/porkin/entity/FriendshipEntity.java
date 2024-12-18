package com.porkin.porkin.entity;

import com.porkin.porkin.dto.FriendshipDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "friendship")
public class FriendshipEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "idUser", referencedColumnName = "username")
  private PersonEntity fkPersonUser;

  @ManyToOne
  @JoinColumn(name = "idFriend", referencedColumnName = "username")
  private PersonEntity fkPersonFriend;


  // friendshipEntity constructors

  public FriendshipEntity(FriendshipDTO friendshipDTO, PersonEntity fkPersonUser, PersonEntity fkPersonFriend) {
    this.fkPersonUser = fkPersonUser;
    this.fkPersonFriend = fkPersonFriend;
  }

  public FriendshipEntity() {

  }

  // getters and setters

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
    if (o == null || getClass() != o.getClass()) return false;
    FriendshipEntity that = (FriendshipEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
