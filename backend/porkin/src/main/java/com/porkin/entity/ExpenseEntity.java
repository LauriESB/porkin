package com.porkin.entity;

import com.porkin.dto.ExpenseDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "expense")
public class ExpenseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private int totalCost;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDate creationDate = LocalDate.now();

  @Column(nullable = false)
  private LocalDate dueDate;

  @Column(nullable = false)
  private boolean completed = false;

  @ManyToOne
  @JoinColumn(name = "idExpenseCreator")
  private PersonEntity expenseCreator;

  // despesEntity constructors

  public ExpenseEntity(ExpenseDTO expenseDTO) {
    BeanUtils.copyProperties(expenseDTO, this);
  }

  public ExpenseEntity() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(int valorTotal) {
    this.totalCost = totalCost;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public PersonEntity getExpenseCreator() {
    return expenseCreator;
  }

  public void setExpenseCreator(PersonEntity expenseCreator) {
    this.expenseCreator = expenseCreator;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExpenseEntity that = (ExpenseEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
