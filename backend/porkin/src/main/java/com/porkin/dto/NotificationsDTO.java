package com.porkin.dto;

import com.porkin.entity.NotificationsEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class NotificationsDTO {

  private Long id;

  private Long idUser;

  private String message;

  private LocalDate creationDate;

  private boolean read;

  // constructors

  public NotificationsDTO(NotificationsEntity notificationsEntity) {
    BeanUtils.copyProperties(notificationsEntity, this);
  }

  public NotificationsDTO() {}

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

}
