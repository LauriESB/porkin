package com.porkin.dto;

import com.porkin.entity.FriendRequestEntity;
import com.porkin.entity.PersonEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class FriendRequestDTO {

  private Long id;

  private Long personRequester;

  private Long personReceiver;

  private String message;

  private String status;

  private LocalDateTime creationDate;

  //

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

  public Long getPersonRequester() {
    return personRequester;
  }

  public void setPersonRequester(Long personRequester) {
    this.personRequester = personRequester;
  }

  public Long getPersonReceiver() {
    return personReceiver;
  }

  public void setPersonReceiver(Long personReceiver) {
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
