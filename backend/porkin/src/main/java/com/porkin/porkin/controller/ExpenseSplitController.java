package com.porkin.porkin.controller;

import com.porkin.porkin.dto.ExpenseSplitDTO;
import com.porkin.porkin.service.ExpenseSplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/expenseSplit")
public class ExpenseSplitController {

  @Autowired
  private ExpenseSplitService expenseSplitService;

  @GetMapping("/{id}")
  public List<ExpenseSplitDTO> listAll(@PathVariable("id") Long id) {
    return expenseSplitService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody ExpenseSplitDTO expenseSplitDTO) {
    expenseSplitService.insert(expenseSplitDTO);
  }

  @PutMapping("/{id}")
  public ExpenseSplitDTO update(@PathVariable("id") Long id, @RequestBody ExpenseSplitDTO expenseSplitDTO) {
    return expenseSplitService.update(id, expenseSplitDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    expenseSplitService.delete(id);
    return ResponseEntity.ok().build();
  }

}
