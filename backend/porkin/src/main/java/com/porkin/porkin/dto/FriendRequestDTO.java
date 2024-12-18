package com.porkin.porkin.dto;

import com.porkin.porkin.entity.FriendRequestEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class FriendRequestDTO {

  private Long id;

  private String personRequester;

  private String personReceiver;

  private String message;

  private String status;

  private LocalDateTime creationDate;

  // constructors

  public FriendRequestDTO(FriendRequestEntity friendEntity) {
    BeanUtils.copyProperties(friendEntity, this);
  }

  public FriendRequestDTO() {

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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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

}
