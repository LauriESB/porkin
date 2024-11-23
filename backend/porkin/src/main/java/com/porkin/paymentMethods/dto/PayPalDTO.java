package com.porkin.paymentMethods.dto;

import com.porkin.paymentMethods.entity.PayPalEntity;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class PayPalDTO {

  private Long id;

  private Long idUser;

  private String payPalKey;

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

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public String getPayPalKey() {
    return payPalKey;
  }

  public void setPayPalKey(String payPalKey) {
    this.payPalKey = payPalKey;
  }

  // equals and hashCode

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PayPalDTO payPalDTO = (PayPalDTO) o;
    return Objects.equals(id, payPalDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}