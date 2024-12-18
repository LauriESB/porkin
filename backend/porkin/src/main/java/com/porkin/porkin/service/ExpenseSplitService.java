package com.porkin.porkin.service;

import com.porkin.porkin.dto.ExpenseSplitDTO;
import com.porkin.porkin.entity.ExpenseSplitEntity;
import com.porkin.porkin.repository.ExpenseSplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseSplitService {

  @Autowired
  public ExpenseSplitRepository expenseSplitRepository;

  public List<ExpenseSplitDTO> listAll() {
    List<ExpenseSplitEntity> splitEntities = expenseSplitRepository.findAll();
    return splitEntities.stream().map(ExpenseSplitDTO::new).toList();
  }

  public void insert(ExpenseSplitDTO expenseSplitDTO) {
    ExpenseSplitEntity expenseSplitEntity = new ExpenseSplitEntity(expenseSplitDTO);
    expenseSplitRepository.save(expenseSplitEntity);
  }

  public ExpenseSplitDTO update(Long id, ExpenseSplitDTO expenseSplitDTO) {
    ExpenseSplitEntity expenseSplitEntity = expenseSplitRepository.findById(id).get();

    if(expenseSplitDTO.isPaid() != expenseSplitEntity.isPaid()) {
      expenseSplitEntity.setPaid(expenseSplitDTO.isPaid());
    }

    return new ExpenseSplitDTO(expenseSplitRepository.save(expenseSplitEntity));
  }

  public void delete(Long id) {
    ExpenseSplitEntity expenseSplitEntity = expenseSplitRepository.findById(id).get();
    expenseSplitRepository.delete(expenseSplitEntity);
  }

  public ExpenseSplitDTO findById(Long id) {
    return new ExpenseSplitDTO(expenseSplitRepository.findById(id).get());
  }

}
