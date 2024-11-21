package com.porkin.paymentMethods.dto;

import com.porkin.paymentMethods.entity.PixEntity;
import org.springframework.beans.BeanUtils;

public class PixDTO {

  private Long id;

  private Long idUser;

  private String pixKey;

    // constructors

  public PixDTO(PixEntity pixEntity) {
    BeanUtils.copyProperties(pixEntity, this);
  }

  public PixDTO() {

  }

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

  public String getPixKey() {
    return pixKey;
  }

  public void setPixKey(String pixKey) {
    this.pixKey = pixKey;
  }

}
