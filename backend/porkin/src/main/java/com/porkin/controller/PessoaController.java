package com.porkin.controller;

import com.porkin.dto.PessoaDTO;
import com.porkin.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PessoaController {

  @Autowired
  private PessoaService pessoaService;

  //@GetMapping
  //public List<PessoaDTO> listAll() {
  //  return pessoaService.listAll();
  //}

  @PostMapping
  public void insert(@RequestBody PessoaDTO pessoaDTO) {
    pessoaService.insert(pessoaDTO);
  }

  @PutMapping
  public PessoaDTO update(@RequestBody PessoaDTO pessoaDTO) {
    return pessoaService.update(pessoaDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    pessoaService.delete(id);
    return ResponseEntity.ok().build();
  }

}
