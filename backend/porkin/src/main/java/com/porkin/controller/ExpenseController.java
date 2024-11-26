package com.porkin.controller;

import com.porkin.dto.ExpenseDTO;
import com.porkin.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {

  @Autowired
  private ExpenseService expenseService;

  @GetMapping
  public List<ExpenseDTO> listAll() {
    return expenseService.listAll();
  }

  @GetMapping("/{username}")
  public List<ExpenseDTO> listUserExpenses(@PathVariable("username") String username) {
    return expenseService.listUserExpenses(username);
  }

  @PostMapping
  public void insert(@RequestBody ExpenseDTO expenseDTO) {
    expenseService.insert(expenseDTO);
  }

  @PutMapping("/{idExpenseCreator}")
  public ExpenseDTO update(@PathVariable String idExpenseCreator, @RequestBody ExpenseDTO expenseDTO) {
    return expenseService.update(idExpenseCreator, expenseDTO);
  }

  @DeleteMapping("/{idExpenseCreator}")
  public ResponseEntity<Void> delete(@PathVariable("idExpenseCreator") String idExpenseCreator) {
    expenseService.delete(idExpenseCreator);
    return ResponseEntity.ok().build();
  }

}
