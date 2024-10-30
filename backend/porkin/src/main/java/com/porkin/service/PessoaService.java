package com.porkin.service;

import com.porkin.dto.PessoaDTO;
import com.porkin.entity.PessoaEntity;
import com.porkin.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

  @Autowired
  public PessoaRepository pessoaRepository;

  public List<PessoaDTO> listAll() {
    List<PessoaEntity> pessoaEntities = pessoaRepository.findAll();
    return pessoaEntities.stream().map(PessoaDTO::new).toList();
  }

  public void insert(PessoaDTO pessoaDTO) {
    PessoaEntity pessoaEntity = new PessoaEntity(pessoaDTO);
    pessoaRepository.save(pessoaEntity);
  }

  public PessoaDTO update(PessoaDTO pessoaDTO) {
    PessoaEntity pessoaEntity = new PessoaEntity(pessoaDTO);
    return new PessoaDTO(pessoaRepository.save(pessoaEntity));
  }
  public void delete(Long id) {
    PessoaEntity pessoaEntity = pessoaRepository.findById(id).get();
    pessoaRepository.delete(pessoaEntity);
  }

  public PessoaDTO findById(Long id) {
    return new PessoaDTO(pessoaRepository.findById(id).get());
  }

}
