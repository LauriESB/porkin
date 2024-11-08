package com.porkin.controller;

import com.porkin.dto.ExpenseSplitDTO;
import com.porkin.service.ExpenseSplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/expensesplit")
public class ExpenseSplitController {

  @Autowired
  private ExpenseSplitService expenseSplitService;

  @GetMapping
  public List<ExpenseSplitDTO> listAll() {
    return expenseSplitService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody ExpenseSplitDTO expenseSplitDTO) {
    expenseSplitService.insert(expenseSplitDTO);
  }

  @PutMapping("/{id}")
  public ExpenseSplitDTO update(@PathVariable Long id, @RequestBody ExpenseSplitDTO expenseSplitDTO) {
    return expenseSplitService.update(id, expenseSplitDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    expenseSplitService.delete(id);
    return ResponseEntity.ok().build();
  }

}
