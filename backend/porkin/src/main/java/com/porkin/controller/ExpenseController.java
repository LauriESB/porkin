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

  @PostMapping
  public void insert(@RequestBody ExpenseDTO expenseDTO) {
    expenseService.insert(expenseDTO);
  }

  @PutMapping
  public ExpenseDTO update(@RequestBody ExpenseDTO expenseDTO) {
    return expenseService.update(expenseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    expenseService.delete(id);
    return ResponseEntity.ok().build();
  }

}
