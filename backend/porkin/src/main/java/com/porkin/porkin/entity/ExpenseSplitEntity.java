package com.porkin.porkin.entity;

import com.porkin.porkin.dto.ExpenseSplitDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "expenseSplit")
public class ExpenseSplitEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "expenseId")
  private ExpenseEntity expense;

  @ManyToOne
  @JoinColumn(name = "person")
  private PersonEntity person;

  @Column(nullable = false)
  private boolean paid;

  @Column(nullable = false)
  private double valueToPay;

  @Column(nullable = false)
  private double percentage;

  // divisaoEntity constructors

  public ExpenseSplitEntity(ExpenseSplitDTO splitDTO) {
    BeanUtils.copyProperties(splitDTO, this);
  }

  public ExpenseSplitEntity() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ExpenseEntity getExpense() {
    return expense;
  }

  public void setExpense(ExpenseEntity expense) {
    this.expense = expense;
  }

  public String getPerson() {
    return person.getUsername();
  }

  public void setPerson(PersonEntity person) {
    this.person = person;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
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

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    ExpenseSplitEntity that = (ExpenseSplitEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
