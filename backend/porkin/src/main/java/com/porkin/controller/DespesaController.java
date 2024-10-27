package com.porkin.controller;

import com.porkin.dto.DespesaDTO;
import com.porkin.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/despesa")
public class DespesaController {

  @Autowired
  private DespesaService despesaService;

  //@GetMapping
  //public List<DespesaDTO> listAll() {
  //  return despesaService.listAll();
  //}

  @PostMapping
  public void insert(@RequestBody DespesaDTO despesaDTO) {
    despesaService.insert(despesaDTO);
  }

  @PutMapping
  public DespesaDTO update(@RequestBody DespesaDTO despesaDTO) {
    return despesaService.update(despesaDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    despesaService.delete(id);
    return ResponseEntity.ok().build();
  }

}
