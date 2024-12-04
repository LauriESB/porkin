package com.porkin.dto;

import com.porkin.entity.NotificationsEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class NotificationsDTO {

  private Long id;

  private String message;

  private LocalDate creationDate;

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

}
