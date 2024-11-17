package com.porkin.service;

import com.porkin.dto.ExpenseDTO;
import com.porkin.entity.ExpenseEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.ExpenseRepository;
import com.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

  @Autowired
  public ExpenseRepository expenseRepository;

  @Autowired
  public PersonRepository personRepository;

  public List<ExpenseDTO> listAll() {
    List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
    return expenseEntities.stream().map(ExpenseDTO::new).toList();
  }

  public void insert(ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = new ExpenseEntity(expenseDTO);
    expenseEntity.setCreationDate(LocalDate.now());
    expenseEntity.setCompleted(false);

    PersonEntity idexpensecreator = personRepository.findById(expenseDTO.getIdExpenseCreator()).get();
    expenseEntity.setIdExpenseCreator(idexpensecreator);

    expenseRepository.save(expenseEntity);
  }

  public ExpenseDTO update(Long id, ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = expenseRepository.findById(id).get();

    if(expenseDTO.getIdExpenseCreator() != null) {
      ExpenseEntity expenseDTOtoEntity = new ExpenseEntity(expenseDTO);
      expenseEntity.setIdExpenseCreator(expenseDTOtoEntity.getIdExpenseCreator());
    }

    expenseEntity.setTotalCost(expenseDTO.getTotalCost());

    if(expenseDTO.getTitle() != null) {
      expenseEntity.setTitle(expenseDTO.getTitle());
    }

    if(expenseDTO.getCreationDate() != null) {
      //exceçao
    }

    if(expenseDTO.getDueDate() != null) {
      expenseEntity.setDueDate(expenseDTO.getDueDate());
    }

    if(expenseDTO.isCompleted() != expenseEntity.isCompleted()) {
      expenseEntity.setCompleted(expenseDTO.isCompleted());
    }

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
