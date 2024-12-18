package com.porkin.porkin.paymentMethods.entity;

import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.paymentMethods.dto.PayPalDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "payPal")
public class PayPalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String type;

  @Column
  private String payPal;

  @OneToOne
  @JoinColumn(name = "username")
  private PersonEntity username;

  // constructors

  public PayPalEntity(PayPalDTO payPalDTO) {
    BeanUtils.copyProperties(payPalDTO, this);
  }

  public PayPalEntity() {

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

  public String getPayPal() {
    return payPal;
  }

  public void setPayPal(String payPal) {
    this.payPal = payPal;
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
    PayPalEntity that = (PayPalEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
