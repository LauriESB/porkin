package com.porkin.paymentMethods.service;

import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.dto.PixDTO;
import com.porkin.paymentMethods.entity.PixEntity;
import com.porkin.paymentMethods.repository.PixRepository;
import com.porkin.repository.PersonRepository;
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
    PersonEntity person = personRepository.findById(pixDTO.getIdUser())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    PixEntity pixEntity = new PixEntity(pixDTO);
    pixEntity.setIdUser(person);
    pixEntity.setType("Chave Pix");

    person.setPix(pixEntity);

    pixRepository.save(pixEntity);
    personRepository.save(person);
  }

  public PixDTO updatePix(Long id, PixDTO pixDTO) {
    PixEntity pixEntity = pixRepository.findById(id).get();
    PersonEntity person = personRepository.findById(pixDTO.getId()).get();

    if (pixDTO.getPixKey() != null) {
      pixEntity.setPixKey(pixDTO.getPixKey());
      person.setPix(pixEntity);
    }

    personRepository.save(person);
    return new PixDTO(pixRepository.save(pixEntity));

  }

  public void deletePix(Long id, Long idUser) {
    PixEntity pixEntity = pixRepository.findById(id).get();
    PersonEntity person = personRepository.findById(idUser).get();

    if (!pixEntity.getIdUser().equals(idUser)) {
      throw new RuntimeException("PayPal does not belong to the specified user");
    }

    person.setPix(null);

    personRepository.save(person);

    //pixRepository.delete(pixEntity);
  }


}
