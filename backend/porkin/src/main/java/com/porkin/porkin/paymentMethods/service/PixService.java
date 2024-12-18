package com.porkin.porkin.paymentMethods.service;

import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.paymentMethods.dto.PixDTO;
import com.porkin.porkin.paymentMethods.entity.PixEntity;
import com.porkin.porkin.paymentMethods.repository.PixRepository;
import com.porkin.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixService {

  @Autowired
  public PixRepository pixRepository;

  @Autowired
  public PersonRepository personRepository;

  public List<PixDTO> listAll() {
    List<PixEntity> pixEntities = pixRepository.findAll();
    return pixEntities.stream().map(PixDTO::new).toList();
  }

  public void addPix(PixDTO pixDTO) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(pixDTO.getUsername());

    PixEntity pixEntity = new PixEntity(pixDTO);
    pixEntity.setPix(pixDTO.getPix());
    pixEntity.setUsername(person);
    pixEntity.setType("Chave Pix");

    person.setPix(pixEntity);

    pixRepository.save(pixEntity);
    personRepository.save(person);
  }

  public PixDTO updatePix(PixDTO pixDTO) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(pixDTO.getUsername());
    PixEntity pixEntity = pixRepository.findByUsername(person).get();

    if (pixDTO.getPix() != null) {
      pixEntity.setPix(pixDTO.getPix());
      person.setPix(pixEntity);
    }

    personRepository.save(person);
    return new PixDTO(pixRepository.save(pixEntity));

  }

  public void deletePix(Long id, Long idUser) {
    PixEntity pixEntity = pixRepository.findById(id).get();
    PersonEntity person = personRepository.findById(idUser).get();

    if (!pixEntity.getUsername().equals(idUser)) {
      throw new RuntimeException("PayPal does not belong to the specified user");
    }

    person.setPix(null);

    personRepository.save(person);

  }

}
