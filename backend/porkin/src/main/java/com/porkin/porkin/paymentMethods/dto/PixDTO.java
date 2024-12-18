package com.porkin.porkin.paymentMethods.dto;

import com.porkin.porkin.paymentMethods.entity.PixEntity;
import org.springframework.beans.BeanUtils;

public class PixDTO {

  private Long id;

  private String type;

  private String username;

  private String pix;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPix() {
    return pix;
  }

  public void setPix(String pix) {
    this.pix = pix;
  }

}
