package com.porkin.entity;

import com.porkin.dto.FriendRequestDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "friendRequest")
public class FriendRequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "requesterId")
  private PersonEntity personRequester;

  @ManyToOne
  @JoinColumn(name = "receiverId")
  private PersonEntity personReceiver;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private LocalDateTime creationDate;

  // constructors

  public FriendRequestEntity(FriendRequestDTO friendDTO) {
    BeanUtils.copyProperties(friendDTO, this);
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

  public PersonEntity getPersonRequester() {
    return personRequester;
  }

  public void setPersonRequester(PersonEntity personRequester) {
    this.personRequester = personRequester;
  }

  public PersonEntity getPersonReceiver() {
    return personReceiver;
  }

  public void setPersonReceiver(PersonEntity personReceiver) {
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
