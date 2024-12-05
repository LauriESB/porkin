package com.porkin.paymentMethods.entity;

import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.dto.PayPalDTO;
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

  @Column(nullable = false)
  private String payPalKey;

  @OneToOne
  @JoinColumn(name = "idUser")
  private PersonEntity idUser;

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

  public String getPayPalKey() {
    return payPalKey;
  }

  public void setPayPalKey(String payPalKey) {
    this.payPalKey = payPalKey;
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
    PayPalEntity that = (PayPalEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
