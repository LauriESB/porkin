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
    // Busque a PersonEntity antes de criar o PixEntity
    PersonEntity person = personRepository.findById(pixDTO.getIdUser())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    // Crie o PixEntity e defina o usuário
    PixEntity pixEntity = new PixEntity(pixDTO);
    pixEntity.setIdUser(person);
    pixEntity.setType("Chave Pix");

    // Adicione o PixEntity à lista de métodos de pagamento do usuário
    person.setPix(pixEntity);

    // Salve o PixEntity e a PersonEntity
    pixRepository.save(pixEntity);
    personRepository.save(person);
  }

  public PixDTO updatePix(Long id, PixDTO pixDTO) {
    PixEntity pixEntity = pixRepository.findById(id).get();

    if (pixDTO.getPixKey() != null) {
      pixEntity.setPixKey(pixDTO.getPixKey());
    }

    return new PixDTO(pixRepository.save(pixEntity));

  }

  public void deletePix(Long id) {
    PixEntity pixEntity = pixRepository.findById(id).get();
    pixRepository.delete(pixEntity);
  }


}
