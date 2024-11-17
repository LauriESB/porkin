package com.porkin.dto;

import com.porkin.entity.ExpenseEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class ExpenseDTO {

  private Long id;

  private double totalCost;

  private String title;

  private LocalDate creationDate;

  private LocalDate dueDate;

  private boolean completed;

  private Long idExpenseCreator;

  // ExpenseDTO constructor

  public ExpenseDTO(ExpenseEntity expenseEntity) {
    BeanUtils.copyProperties(expenseEntity, this);
  }

  public ExpenseDTO() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
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

  public Long getIdExpenseCreator() {
    return idExpenseCreator;
  }

  public void setIdExpenseCreator(Long idExpenseCreator) {
    this.idExpenseCreator = idExpenseCreator;
  }

}
