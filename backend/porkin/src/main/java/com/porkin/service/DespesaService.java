package com.porkin.service;

import com.porkin.dto.AmizadeDTO;
import com.porkin.dto.DespesaDTO;
import com.porkin.entity.AmizadeEntity;
import com.porkin.entity.DespesaEntity;
import com.porkin.repository.DespesaRepository;

import java.util.List;

public class DespesaService {

  public DespesaRepository despesaRepository;

  public List<DespesaDTO> listAll() {
    List<DespesaEntity> despesaDTOS = despesaRepository.findAll();
    return despesaDTOS.stream().map(DespesaDTO::new).toList();
  }

  public void insert(DespesaDTO despesaDTO) {
    DespesaEntity despesaEntity = new DespesaEntity(despesaDTO);
    despesaRepository.save(despesaEntity);
  }

  public DespesaDTO update(DespesaDTO despesaDTO) {
    DespesaEntity despesaEntity = new DespesaEntity(despesaDTO);
    return new DespesaDTO(despesaRepository.save(despesaEntity));
  }

  public void delete(Long id) {
    DespesaEntity despesaEntity = despesaRepository.findById(id).get();
    despesaRepository.delete(despesaEntity);
  }

  public DespesaDTO findById(Long id) {
    return new DespesaDTO(despesaRepository.findById(id).get());
  }

}
