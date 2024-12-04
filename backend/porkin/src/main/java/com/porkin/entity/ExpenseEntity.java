package com.porkin.entity;

import com.porkin.dto.ExpenseDTO;
import com.porkin.dto.ExpenseSplitDTO;
import com.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.paymentMethods.entity.PixEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "expense")
public class ExpenseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Double totalCost;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDate creationDate;

  @Column(nullable = false)
  private LocalDate dueDate;

  @Column(nullable = false)
  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "idExpenseCreator")
  private PersonEntity idExpenseCreator;

  private boolean notificationSent;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "pixId")
  private PixEntity pix;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "paypalId")
  private PayPalEntity paypal;

  @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
  private List<ExpenseSplitEntity> expenseDetails;



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
    return idExpenseCreator.getUsername();
  }

  public void setIdExpenseCreator(PersonEntity idExpenseCreator) {
    this.idExpenseCreator = idExpenseCreator;
  }

  public boolean isNotificationSent() {
    return notificationSent;
  }

  public void setNotificationSent(boolean notificationSent) {
    this.notificationSent = notificationSent;
  }

  public String getPix() {
    return pix != null ? pix.getPixKey() : null;
  }

  public void setPix(PixEntity pix) {
    this.pix = pix;
  }

  public String getPaypal() {
    return paypal != null ? paypal.getPayPalKey() : null;
  }

  public void setPaypal(PayPalEntity paypal) {
    this.paypal = paypal;
  }

  public List<ExpenseSplitDTO> getExpenseDetails() {
    return this.expenseDetails.stream().map(details -> new ExpenseSplitDTO(
            details.getPerson(),
            details.getValueToPay(),
            details.getPercentage(),
            details.isPaid()
        )).toList();
  }

  public void setExpenseDetails(List<ExpenseSplitEntity> expenseDetails) {
    this.expenseDetails = expenseDetails;
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
