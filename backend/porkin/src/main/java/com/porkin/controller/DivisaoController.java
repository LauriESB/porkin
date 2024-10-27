package com.porkin.controller;

import com.porkin.dto.DivisaoDTO;
import com.porkin.service.DivisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DivisaoController {

  @Autowired
  private DivisaoService divisaoService;

  //@GetMapping
  //public List<DivisaoDTO> listAll() {
  //  return divisaoService.listAll();
  //}

  @PostMapping
  public void insert(@RequestBody DivisaoDTO divisaoDTO) {
    divisaoService.insert(divisaoDTO);
  }

  @PutMapping
  public DivisaoDTO update(@RequestBody DivisaoDTO divisaoDTO) {
    return divisaoService.update(divisaoDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    divisaoService.delete(id);
    return ResponseEntity.ok().build();
  }

}
