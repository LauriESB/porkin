package com.porkin.dto;

import com.porkin.entity.ExpenseSplitEntity;
import org.springframework.beans.BeanUtils;

public class ExpenseSplitDTO {

  private Long person;

  private double valueToPay;

  private double percentage;

  private boolean paid;

  // ExpenseSplitDTO constructors

  public ExpenseSplitDTO(ExpenseSplitEntity expenseSplitEntity) {
    BeanUtils.copyProperties(expenseSplitEntity, this);
  }

  public ExpenseSplitDTO(Long person, double valueToPay, double percentage, boolean paid) {
    this.person = person;
    this.valueToPay = valueToPay;
    this.percentage = percentage;
    this.paid = paid;
  }

  public ExpenseSplitDTO() {

  }

  // getters and setters

  public Long getPerson() {
    return person;
  }

  public void setPerson(Long person) {
    this.person = person;
  }

  public double getValueToPay() {
    return valueToPay;
  }

  public void setValueToPay(double valueToPay) {
    this.valueToPay = valueToPay;
  }

  public double getPercentage() {
    return percentage;
  }

  public void setPercentage(double percentage) {
    this.percentage = percentage;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

}
