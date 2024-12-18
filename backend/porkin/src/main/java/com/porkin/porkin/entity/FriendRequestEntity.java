package com.porkin.porkin.entity;

import com.porkin.porkin.dto.FriendRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "friendRequest")
public class FriendRequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String personRequester;

  private String personReceiver;

  @Column
  private String status;

  @Column
  private LocalDateTime creationDate;

  @Column
  private String message;

  // constructors

  public FriendRequestEntity(FriendRequestDTO friendDTO) {
    this.personRequester = friendDTO.getPersonRequester();
    this.personReceiver = friendDTO.getPersonReceiver();
    //BeanUtils.copyProperties(friendDTO, this);
  }

  public FriendRequestEntity() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPersonRequester() {
    return personRequester;
  }

  public void setPersonRequester(String personRequester) {
    this.personRequester = personRequester;
  }

  public String getPersonReceiver() {
    return personReceiver;
  }

  public void setPersonReceiver(String personReceiver) {
    this.personReceiver = personReceiver;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FriendRequestEntity that = (FriendRequestEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
