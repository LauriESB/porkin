package com.porkin.service;

import com.porkin.dto.ExpenseDTO;
import com.porkin.entity.ExpenseEntity;
import com.porkin.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

  @Autowired
  public ExpenseRepository expenseRepository;

  public List<ExpenseDTO> listAll() {
    List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
    return expenseEntities.stream().map(ExpenseDTO::new).toList();
  }

  public void insert(ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = new ExpenseEntity(expenseDTO);
    expenseRepository.save(expenseEntity);
  }

  public ExpenseDTO update(ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = new ExpenseEntity(expenseDTO);
    return new ExpenseDTO(expenseRepository.save(expenseEntity));
  }

  public void delete(Long id) {
    ExpenseEntity expenseEntity = expenseRepository.findById(id).get();
    expenseRepository.delete(expenseEntity);
  }

  public ExpenseDTO findById(Long id) {
    return new ExpenseDTO(expenseRepository.findById(id).get());
  }

}
