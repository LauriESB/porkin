package com.porkin.repository;

import com.porkin.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
  Optional<ExpenseEntity> findByIdExpenseCreator_Username(String idUser);

  @Query("SELECT e FROM ExpenseEntity e JOIN e.expenseDetails ed WHERE ed.person.username = :username")
  List<ExpenseEntity> findAllByParticipantUsername(@Param("username") String username);
}
