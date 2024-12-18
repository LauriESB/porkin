package com.porkin.porkin.service;

import com.porkin.porkin.dto.PersonDTO;
import com.porkin.porkin.email.EmailService;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

  @Autowired
  public PersonRepository personRepository;

  @Autowired
  private EmailService emailService;


  public void sendRecoveryCode(String email) {
    PersonEntity user = personRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    String recoveryCode = generateRecoveryCode();
    user.setRecoveryCode(recoveryCode);
    user.setRecoveryCodeExpiration(LocalDateTime.now().plusMinutes(15));
    personRepository.save(user);

    emailService.sendEmail(user.getEmail(), "Código de Recuperação", "Seu código de recuperação é: " + recoveryCode);
  }

  public void resetPassword(String email, String recoveryCode, String newPassword) {
    PersonEntity user = personRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (!recoveryCode.equals(user.getRecoveryCode()) || LocalDateTime.now().isAfter(user.getRecoveryCodeExpiration())) {
      throw new RuntimeException("Código de recuperação inválido ou expirado");
    }

    user.setPassword(newPassword);
    user.setRecoveryCode(null);
    user.setRecoveryCodeExpiration(null);
    personRepository.save(user);
  }

  private String generateRecoveryCode() {
    return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
  }



  public List<PersonDTO> listAll() {
    List<PersonEntity> personEntities = personRepository.findAll();
    return personEntities.stream().map(PersonDTO::new).toList();
  }

  public PersonDTO findByUsername(String username) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(username);
    return new PersonDTO(person);
  }

  /*
  public void insert(PersonDTO personDTO) {
    PersonEntity personEntity = new PersonEntity(personDTO);
    personRepository.save(personEntity);
  }

   */

  public PersonDTO update(String username, PersonDTO personDTO) {
    PersonEntity personEntity = (PersonEntity) personRepository.findByUsername(username);

    if(personDTO.getName() != null) {
      personEntity.setName(personDTO.getName());
    }

    if(personDTO.getUsername() != null) {
      personEntity.setPassword(personDTO.getUsername());
    }

    if(personDTO.getEmail() != null) {
      personEntity.setEmail(personDTO.getEmail());
    }

    if(personDTO.getPassword() != null) {
      personEntity.setPassword(personDTO.getPassword());
    }

    return new PersonDTO(personRepository.save(personEntity));

  }

  public void delete(String username) {
    PersonEntity personEntity = (PersonEntity) personRepository.findByUsername(username);
    personRepository.delete(personEntity);
  }

  public PersonDTO findById(Long id) {
    return new PersonDTO(personRepository.findById(id).get());
  }

}
