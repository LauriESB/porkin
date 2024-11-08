package com.porkin.service;

import com.porkin.dto.PersonDTO;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

  @Autowired
  public PersonRepository personRepository;

  public List<PersonDTO> listAll() {
    List<PersonEntity> personEntities = personRepository.findAll();
    return personEntities.stream().map(PersonDTO::new).toList();
  }

  public void insert(PersonDTO personDTO) {
    PersonEntity personEntity = new PersonEntity(personDTO);
    personRepository.save(personEntity);
  }

  public PersonDTO update(Long id, PersonDTO personDTO) {
    PersonEntity personEntity = personRepository.findById(id).get();

    if(personDTO.getEmail() != null) {
      personEntity.setEmail(personDTO.getEmail());
    }

    if(personDTO.getName() != null) {
      personEntity.setName(personDTO.getName());
    }

    if(personDTO.getPassword() != null) {
      personEntity.setPassword(personDTO.getPassword());
    }


    return new PersonDTO(personRepository.save(personEntity));
  }
  public void delete(Long id) {
    PersonEntity personEntity = personRepository.findById(id).get();
    personRepository.delete(personEntity);
  }

  public PersonDTO findById(Long id) {
    return new PersonDTO(personRepository.findById(id).get());
  }

}
