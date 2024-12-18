package com.porkin.porkin.dto;

import com.porkin.porkin.entity.ExpenseEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;

public class ExpenseDTO {

  private Long id;

  private Double totalCost;

  private String title;

  private LocalDate creationDate;

  private LocalDate dueDate;

  private boolean completed;

  //private String message;

  private String idExpenseCreator;

  private boolean notificationSend;

  private String pix;

  private String paypal;

  private List<ExpenseSplitDTO> expenseDetails;

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

  public Double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(Double totalCost) {
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

  public String getIdExpenseCreator() {
    return idExpenseCreator;
  }

  public void setIdExpenseCreator(String idExpenseCreator) {
    this.idExpenseCreator = idExpenseCreator;
  }

  public List<ExpenseSplitDTO> getExpenseDetails() {
    return expenseDetails;
  }

  public void setExpenseDetails(List<ExpenseSplitDTO> expenseDetails) {
    this.expenseDetails = expenseDetails;
  }

  public boolean isNotificationSend() {
    return notificationSend;
  }

  public void setNotificationSend(boolean notificationSend) {
    this.notificationSend = notificationSend;
  }

  public String getPix() {
    return pix;
  }

  public void setPix(String pix) {
    this.pix = pix;
  }

  public String getPaypal() {
    return paypal;
  }

  public void setPaypal(String paypal) {
    this.paypal = paypal;
  }

}
