package com.porkin.entity;

import com.porkin.dto.ExpenseDTO;
import com.porkin.dto.ExpenseSplitDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "expense")
public class ExpenseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private double totalCost;

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
    return idExpenseCreator.getId();
  }

  public void setIdExpenseCreator(PersonEntity idExpenseCreator) {
    this.idExpenseCreator = idExpenseCreator;
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
