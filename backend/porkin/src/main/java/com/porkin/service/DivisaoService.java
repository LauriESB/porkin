package com.porkin.service;

import com.porkin.dto.DivisaoDTO;
import com.porkin.entity.DivisaoEntity;
import com.porkin.repository.DivisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisaoService {

  @Autowired
  public DivisaoRepository divisaoRepository;

  public List<DivisaoDTO> listAll() {
    List<DivisaoEntity> divisaoEntities = divisaoRepository.findAll();
    return divisaoEntities.stream().map(DivisaoDTO::new).toList();
  }

  public void insert(DivisaoDTO divisaoDTO) {
    DivisaoEntity divisaoEntity = new DivisaoEntity(divisaoDTO);
    divisaoRepository.save(divisaoEntity);
  }

  public DivisaoDTO update(DivisaoDTO divisaoDTO) {
    DivisaoEntity divisaoEntity = new DivisaoEntity(divisaoDTO);
    return new DivisaoDTO(divisaoRepository.save(divisaoEntity));
  }

  public void delete(Long id) {
    DivisaoEntity divisaoEntity = divisaoRepository.findById(id).get();
    divisaoRepository.delete(divisaoEntity);
  }

  public DivisaoDTO findById(Long id) {
    return new DivisaoDTO(divisaoRepository.findById(id).get());
  }

}
