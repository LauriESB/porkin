package com.porkin.controller;

import com.porkin.dto.AmizadeDTO;
import com.porkin.dto.DespesaDTO;
import com.porkin.service.AmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AmizadeController {

  @Autowired
  private AmizadeService amizadeService;

  @GetMapping
  public List<AmizadeDTO> listAll() {
    return amizadeService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody AmizadeDTO amizadeDTO) {
    amizadeService.insert(amizadeDTO);
  }

  @PutMapping
  public AmizadeDTO update(@RequestBody AmizadeDTO amizadeDTO) {
    return amizadeService.update(amizadeDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    amizadeService.delete(id);
    return ResponseEntity.ok().build();
  }

}
