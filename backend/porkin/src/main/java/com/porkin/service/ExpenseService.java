package com.porkin.service;

import com.porkin.dto.ExpenseDTO;
import com.porkin.entity.ExpenseEntity;
import com.porkin.entity.ExpenseSplitEntity;
import com.porkin.entity.NotificationsEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.paymentMethods.entity.PixEntity;
import com.porkin.repository.ExpenseRepository;
import com.porkin.repository.ExpenseSplitRepository;
import com.porkin.repository.NotificationsRepository;
import com.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

  @Autowired
  public ExpenseRepository expenseRepository;

  @Autowired
  public PersonRepository personRepository;

  @Autowired
  public ExpenseSplitRepository expenseSplitRepository;

  @Autowired
  public NotificationsRepository notificationsRepository;

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

  @Scheduled(cron = "0 0 0 * * ?")
  public void checkDueDateAndNotifyDaily() {
    List<ExpenseEntity> expensesDueToday = expenseRepository.findByDueDate(LocalDate.now());

    for (ExpenseEntity expenses : expensesDueToday) {
      checkDueDateAndNotify(expenses);
    }

  }

  public void checkDueDateAndNotify(ExpenseEntity expenseEntity) {
    if (!expenseEntity.getNotificationSent() && expenseEntity.getDueDate().isEqual(LocalDate.now())) {
      expenseEntity.getExpenseDetails().forEach(split -> {
        PersonEntity participant = personRepository.findByUsername(split.getUsername()).get();

        NotificationsEntity notifications = new NotificationsEntity();
        notifications.setMessage("A despesa '" + expenseEntity.getTitle() + "' vence hoje!");
        notifications.setCreationDate(LocalDateTime.now());
        notifications.setPerson(participant);
        notificationsRepository.save(notifications);
      });

      expenseEntity.setNotificationSent(true);
      expenseRepository.save(expenseEntity);

    }
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

      if (personParticipant.getSplitExpenses() == null) {
        personParticipant.setSplitExpenses(new ArrayList<>());
      }
      personParticipant.getSplitExpenses().add(splitParticipants);

      personParticipant.getExpenses().add(expenseEntity);
    });

    //expenseEntity.setNotificationSent(expenseDTO.getNotificationSent() != null ? expenseDTO.getNotificationSent() : false);
    expenseEntity.setNotificationSent(false);
    expenseEntity.setExpenseDetails(split);
    expenseRepository.save(expenseEntity);
    expenseSplitRepository.saveAll(split);

    checkDueDateAndNotify(expenseEntity);

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
      paypal.setPayPalKey(expenseDTO.getPaypal());

      PersonEntity person = new PersonEntity();
      person = personRepository.findByUsername(expenseDTO.getIdExpenseCreator()).get();

      paypal.setUsername(person);

      expenseEntity.setPaypal(paypal);
    }

    if(expenseDTO.getPix() != null) {
      PixEntity pix = new PixEntity();
      pix.setType("PayPal");
      pix.setPix(expenseDTO.getPix());

      PersonEntity person = new PersonEntity();
      person = personRepository.findByUsername(expenseDTO.getIdExpenseCreator()).get();

      pix.setUsername(person);

      expenseEntity.setPix(pix);
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
        split.setPaid(detailDTO.isPaid());
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
