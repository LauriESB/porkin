package com.porkin.porkin.repository;

import com.porkin.porkin.entity.ExpenseSplitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplitEntity, Long> {

  @Modifying
  @Query("DELETE FROM ExpenseSplitEntity e WHERE e.expense.id = :expenseId")
  void deleteAllByExpenseId(@Param("expenseId") Long expenseId);

}
