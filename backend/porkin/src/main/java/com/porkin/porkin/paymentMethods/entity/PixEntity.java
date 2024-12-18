package com.porkin.porkin.paymentMethods.entity;

import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.paymentMethods.dto.PixDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "pix")
public class PixEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String type;

  @Column
  private String pix;

  @OneToOne
  @JoinColumn(name = "username")
  private PersonEntity username;

  public PixEntity(PixDTO pixDTO) {
    BeanUtils.copyProperties(pixDTO, this);
  }

  public PixEntity() {

  }

  // getters and setters


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPix() {
    return pix;
  }

  public void setPix(String pix) {
    this.pix = pix;
  }

  public String getUsername() {
    return username.getUsername();
  }

  public void setUsername(PersonEntity username) {
    this.username = username;
  }

  // equals and hashCode

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PixEntity pixEntity = (PixEntity) o;
    return Objects.equals(id, pixEntity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
