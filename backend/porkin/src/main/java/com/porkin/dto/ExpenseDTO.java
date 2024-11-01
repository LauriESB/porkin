package com.porkin.dto;

import com.porkin.entity.ExpenseEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class ExpenseDTO {

  private Long id;

  private int totalCost;

  private String title;

  private LocalDate creatinDate;

  private LocalDate dueDate;

  private boolean completed;

  private Long expenseCreator;

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

  public int getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getCreatinDate() {
    return creatinDate;
  }

  public void setCreatinDate(LocalDate creatinDate) {
    this.creatinDate = creatinDate;
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

  public Long getExpenseCreator() {
    return expenseCreator;
  }

  public void setExpenseCreator(Long expenseCreator) {
    this.expenseCreator = expenseCreator;
  }

}
