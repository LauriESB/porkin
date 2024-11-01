package com.porkin.dto;

import com.porkin.entity.ExpenseSplitEntity;
import org.springframework.beans.BeanUtils;

public class ExpenseSplitDTO {

  private long id;

  private Long idFriend;

  private int expensePercentage;

  private boolean paid;

  // ExpenseSplitDTO constructors

  public ExpenseSplitDTO(ExpenseSplitEntity expenseSplitEntity) {
    BeanUtils.copyProperties(expenseSplitEntity, this);
  }

  public ExpenseSplitDTO() {

  }

  // getters and setters

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getIdFriend() {
    return idFriend;
  }

  public void setIdFriend(Long idFriend) {
    this.idFriend = idFriend;
  }

  public int getExpensePercentage() {
    return expensePercentage;
  }

  public void setExpensePercentage(int expensePercentage) {
    this.expensePercentage = expensePercentage;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

}
