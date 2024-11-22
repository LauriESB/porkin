/*
package com.porkin.compositekeys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExpenseSplitIDs implements Serializable {

  private Long idExpense;
  private Long idFriend;

  public ExpenseSplitIDs() {

  }

  public ExpenseSplitIDs(Long idExpense, Long idFriend) {
    this.idExpense = idExpense;
    this.idFriend = idFriend;
  }

  public Long getIdExpense() {
    return idExpense;
  }

  public void setIdExpense(Long idExpense) {
    this.idExpense = idExpense;
  }

  public Long getIdFriend() {
    return idFriend;
  }

  public void setIdFriend(Long idFriend) {
    this.idFriend = idFriend;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExpenseSplitIDs that = (ExpenseSplitIDs) o;
    return Objects.equals(idExpense, that.idExpense) && Objects.equals(idFriend, that.idFriend);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idExpense, idFriend);
  }

}
*/