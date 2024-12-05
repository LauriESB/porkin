package com.porkin.paymentMethods.entity;


import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.dto.PixDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "pix")
public class PixEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String type;

  @Column(unique = true)
  private String pixKey;

  @OneToOne
  @JoinColumn(name = "idUser")
  private PersonEntity idUser;

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

  public String getPixKey() {
    return pixKey;
  }

  public void setPixKey(String pixKey) {
    this.pixKey = pixKey;
  }

  public String getIdUser() {
    return idUser.getUsername();
  }

  public void setIdUser(PersonEntity idUser) {
    this.idUser = idUser;
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
