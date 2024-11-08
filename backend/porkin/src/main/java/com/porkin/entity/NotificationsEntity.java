package com.porkin.entity;

import com.porkin.dto.NotificationsDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notifications")
public class NotificationsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "idUser")
  private PersonEntity idUser;

  @Column(nullable = false)
  private String message;

  @Column(nullable = false)
  private LocalDateTime creationDate;

  @Column(nullable = false)
  private boolean read;

  // constructors

  public NotificationsEntity(NotificationsDTO notificationsDTO) {
    BeanUtils.copyProperties(notificationsDTO, this);
  }

  public NotificationsEntity(boolean read) {
    this.read = read;
  }

  public NotificationsEntity() {}

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PersonEntity getIdUser() {
    return idUser;
  }

  public void setIdUser(PersonEntity idUser) {
    this.idUser = idUser;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NotificationsEntity that = (NotificationsEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
