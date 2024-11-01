package com.porkin.entity;

import com.porkin.compositekeys.ExpenseSplitIDs;
import com.porkin.dto.ExpenseSplitDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "expensesplit")
public class ExpenseSplitEntity {

  @EmbeddedId
  private ExpenseSplitIDs expenseSplitIDs;

  private boolean paid;

  @ManyToOne
  @JoinColumn(name = "idExpenseCreator")
  private PersonEntity idExpenseCreator;

  // divisaoEntity constructors

  public ExpenseSplitEntity(ExpenseSplitDTO splitDTO) {
    BeanUtils.copyProperties(splitDTO, this);
  }

  public ExpenseSplitEntity() {

  }

  // getters and setters


  public ExpenseSplitIDs getExpenseSplitIDs() {
    return expenseSplitIDs;
  }

  public void setExpenseSplitIDs(ExpenseSplitIDs expenseSplitIDs) {
    this.expenseSplitIDs = expenseSplitIDs;
  }

  public PersonEntity getIdExpenseCreator() {
    return idExpenseCreator;
  }

  public void setIdExpenseCreator(PersonEntity idExpenseCreator) {
    this.idExpenseCreator = idExpenseCreator;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExpenseSplitEntity that = (ExpenseSplitEntity) o;
    return Objects.equals(expenseSplitIDs, that.expenseSplitIDs) && Objects.equals(idExpenseCreator, that.idExpenseCreator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expenseSplitIDs, idExpenseCreator);
  }

}
