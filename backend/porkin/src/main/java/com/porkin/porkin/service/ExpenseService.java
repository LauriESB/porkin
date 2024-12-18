package com.porkin.porkin.service;

import com.porkin.porkin.dto.ExpenseDTO;
import com.porkin.porkin.entity.ExpenseEntity;
import com.porkin.porkin.entity.ExpenseSplitEntity;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.porkin.paymentMethods.entity.PixEntity;
import com.porkin.porkin.repository.ExpenseRepository;
import com.porkin.porkin.repository.ExpenseSplitRepository;
import com.porkin.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  public List<ExpenseDTO> listUserExpenses(String username) {
    List<ExpenseEntity> expenses = expenseRepository.findAllByParticipantUsername(username);

    return expenses.stream()
        .map(expense -> new ExpenseDTO(expense)) // Converta para DTO
        .collect(Collectors.toList());
  }

  public void insert(ExpenseDTO expenseDTO) {
    ExpenseEntity expenseEntity = new ExpenseEntity(expenseDTO);
    expenseEntity.setCreationDate(LocalDate.now());
    expenseEntity.setCompleted(false);

    PersonEntity idexpensecreator = (PersonEntity) personRepository.findByUsername(expenseDTO.getIdExpenseCreator());

    expenseEntity.setIdExpenseCreator(idexpensecreator);

    List<ExpenseSplitEntity> split = new ArrayList<>();

    expenseDTO.getExpenseDetails().forEach(expenseSplitDTO -> {

      ExpenseSplitEntity splitParticipants = new ExpenseSplitEntity();

      PersonEntity personParticipant = (PersonEntity) personRepository.findByUsername(expenseSplitDTO.getUsername());

      splitParticipants.setExpense(expenseEntity);
      splitParticipants.setPerson(personParticipant);
      splitParticipants.setValueToPay(expenseSplitDTO.getValueToPay());
      splitParticipants.setPercentage(expenseSplitDTO.getPercentage());
      splitParticipants.setPaid(false);
      split.add(splitParticipants);

      if (personParticipant.getSplitExpenses() == null) {
        personParticipant.setSplitExpenses(new ArrayList<>());
      }
      personParticipant.getSplitExpenses().add(splitParticipants);

      personParticipant.getExpenses().add(expenseEntity);
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

    if(expenseDTO.getPaypal() != null) {
      PayPalEntity paypal = new PayPalEntity();
      paypal.setType("PayPal");
      paypal.setPayPal(expenseDTO.getPaypal());

      PersonEntity person = new PersonEntity();
      person = (PersonEntity) personRepository.findByUsername(expenseDTO.getIdExpenseCreator());

      paypal.setUsername(person);

      expenseEntity.setPaypal(paypal);
    }

    if(expenseDTO.getPix() != null) {
      PixEntity pix = new PixEntity();
      pix.setType("PayPal");
      pix.setPix(expenseDTO.getPix());

      PersonEntity person = new PersonEntity();
      person = (PersonEntity) personRepository.findByUsername(expenseDTO.getIdExpenseCreator());

      pix.setUsername(person);

      expenseEntity.setPix(pix);
    }

    if (expenseDTO.getExpenseDetails() != null) {

      expenseSplitRepository.deleteAllByExpenseId(expenseEntity.getId());

      List<ExpenseSplitEntity> newSplits = expenseDTO.getExpenseDetails().stream().map(detailDTO -> {
        ExpenseSplitEntity split = new ExpenseSplitEntity();

        PersonEntity person = (PersonEntity) personRepository.findByUsername(detailDTO.getUsername());

        split.setExpense(expenseEntity);
        split.setPerson(person);
        split.setValueToPay(detailDTO.getValueToPay());
        split.setPercentage(detailDTO.getPercentage());
        split.setPaid(detailDTO.isPaid());
        return split;
      }).collect(Collectors.toList());

      expenseSplitRepository.saveAll(newSplits);


      if (newSplits.stream().allMatch(ExpenseSplitEntity::isPaid)) {
        expenseEntity.setCompleted(true);
        //expenseEntity.setMessage("Despesa finalizada");
      }


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
