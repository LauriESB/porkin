package com.porkin.service;

import com.porkin.dto.ExpenseDTO;
import com.porkin.dto.ExpenseSplitDTO;
import com.porkin.entity.ExpenseEntity;
import com.porkin.entity.ExpenseSplitEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.ExpenseRepository;
import com.porkin.repository.ExpenseSplitRepository;
import com.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

  @Autowired
  public ExpenseRepository expenseRepository;

  @Autowired
  public PersonRepository personRepository;

  @Autowired
  public ExpenseSplitRepository expenseSplitRepository;

  public List<ExpenseDTO> listAll() {
    List<ExpenseEntity> expenseEntities = expenseRepository.findAll();
    return expenseEntities.stream().map(ExpenseDTO::new).toList();
  }

  public void insert(ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = new ExpenseEntity(expenseDTO);
    expenseEntity.setCreationDate(LocalDate.now());
    expenseEntity.setCompleted(false);

    PersonEntity idexpensecreator = personRepository.findByUsername(expenseDTO.getIdExpenseCreator()).get();
    expenseEntity.setIdExpenseCreator(idexpensecreator);

    List<ExpenseSplitEntity> split = new ArrayList<>();

    //expenseRepository.save(expenseEntity);

    expenseDTO.getExpenseDetails().forEach(expenseSplitDTO -> {

      ExpenseSplitEntity splitParticipants = new ExpenseSplitEntity();

      PersonEntity personParticipant = personRepository.findByUsername(expenseSplitDTO.getUsername()).get();

      splitParticipants.setExpense(expenseEntity);
      splitParticipants.setPerson(personParticipant);
      splitParticipants.setValueToPay(expenseSplitDTO.getValueToPay());
      splitParticipants.setPercentage(expenseSplitDTO.getPercentage());
      splitParticipants.setPaid(false);
      split.add(splitParticipants);
    });

    expenseEntity.setExpenseDetails(split);
    expenseRepository.save(expenseEntity);
    expenseSplitRepository.saveAll(split);
  }

  @Transactional
  public ExpenseDTO update(Long id, ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = expenseRepository.findById(id).get();

    if(expenseDTO.getTotalCost() != null) {
      expenseEntity.setTotalCost(expenseDTO.getTotalCost());
    }

    if(expenseDTO.getTitle() != null) {
      expenseEntity.setTitle(expenseDTO.getTitle());
    }

    if(expenseDTO.getDueDate() != null) {
      expenseEntity.setDueDate(expenseDTO.getDueDate());
    }

    if(expenseDTO.isCompleted() != expenseEntity.isCompleted()) {
      expenseEntity.setCompleted(expenseDTO.isCompleted());
    }

    if (expenseDTO.getExpenseDetails() != null) {

      expenseSplitRepository.deleteAllByExpenseId(expenseEntity.getId());

      List<ExpenseSplitEntity> newSplits = expenseDTO.getExpenseDetails().stream().map(detailDTO -> {
        ExpenseSplitEntity split = new ExpenseSplitEntity();

        PersonEntity person = personRepository.findByUsername(detailDTO.getUsername()).get();

        split.setExpense(expenseEntity);
        split.setPerson(person);
        split.setValueToPay(detailDTO.getValueToPay());
        split.setPercentage(detailDTO.getPercentage());
        return split;
      }).collect(Collectors.toList());

      expenseSplitRepository.saveAll(newSplits);

      expenseEntity.setExpenseDetails(newSplits);
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
