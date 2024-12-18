package com.porkin.porkin.dto;

import com.porkin.porkin.entity.ExpenseSplitEntity;
import org.springframework.beans.BeanUtils;

public class ExpenseSplitDTO {

  private String username;

  private Double valueToPay;

  private Double percentage;

  private boolean paid;

  // ExpenseSplitDTO constructors

  public ExpenseSplitDTO(ExpenseSplitEntity expenseSplitEntity) {
    BeanUtils.copyProperties(expenseSplitEntity, this);
  }

  public ExpenseSplitDTO(String person, Double valueToPay, Double percentage, boolean paid) {
    this.username = person;
    this.valueToPay = valueToPay;
    this.percentage = percentage;
    this.paid = paid;
  }

  public ExpenseSplitDTO() {

  }

  // getters and setters

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Double getValueToPay() {
    return valueToPay;
  }

  public void setValueToPay(Double valueToPay) {
    this.valueToPay = valueToPay;
  }

  public Double getPercentage() {
    return percentage;
  }

  public void setPercentage(Double percentage) {
    this.percentage = percentage;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

}
