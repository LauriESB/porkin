package com.porkin.porkin.paymentMethods.dto;

import com.porkin.porkin.paymentMethods.entity.PayPalEntity;
import org.springframework.beans.BeanUtils;

public class PayPalDTO {

  private Long id;

  private String type;

  private String username;

  private String payPal;

  // constructors

  public PayPalDTO(PayPalEntity payPalEntity) {
    BeanUtils.copyProperties(payPalEntity, this);
  }

  public PayPalDTO() {

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

  public String getPayPal() {
    return payPal;
  }

  public void setPayPal(String payPal) {
    this.payPal = payPal;
  }

}
