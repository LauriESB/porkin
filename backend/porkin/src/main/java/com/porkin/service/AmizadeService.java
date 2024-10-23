package com.porkin.service;

import com.porkin.dto.AmizadeDTO;
import com.porkin.entity.AmizadeEntity;
import com.porkin.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmizadeService { // service chama o repository

  @Autowired
  private AmizadeRepository amizadeRepository;

  // busca dados no entity e retorna dto
  public List<AmizadeDTO> listAll() {
    List<AmizadeEntity> amizadeEntities = amizadeRepository.findAll(); // usa usuário repository pra buscar nas entities
    return amizadeEntities.stream().map(AmizadeDTO::new).toList();
  }

  public void insert(AmizadeDTO amizadeDTO) {
    AmizadeEntity amizadeEntity = new AmizadeEntity(amizadeDTO);
    amizadeRepository.save(amizadeEntity);
  }

  public AmizadeDTO update(AmizadeDTO amizadeDTO) {
    AmizadeEntity amizadeEntity = new AmizadeEntity(amizadeDTO);
    return new AmizadeDTO(amizadeRepository.save(amizadeEntity));
  }
  
  public void delete(Long id) {
    AmizadeEntity amizadeEntity = amizadeRepository.findById(id).get();
    amizadeRepository.delete(amizadeEntity);
  }

  public AmizadeDTO findById(Long id) {
    return new AmizadeDTO(amizadeRepository.findById(id).get());
  }

}
