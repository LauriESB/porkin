package com.porkin.porkin.controller;

import com.porkin.porkin.dto.ExpenseDTO;
import com.porkin.porkin.service.ExpenseService;
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

  @PutMapping("/{id}")
  public ExpenseDTO update(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
    return expenseService.update(id, expenseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    expenseService.delete(id);
    return ResponseEntity.ok().build();
  }

}
