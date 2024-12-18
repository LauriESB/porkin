package com.porkin.porkin.paymentMethods.service;

import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.paymentMethods.dto.PayPalDTO;
import com.porkin.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.porkin.paymentMethods.repository.PayPalRepository;
import com.porkin.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayPalService {

  @Autowired
  public PayPalRepository payPalRepository;

  @Autowired
  public PersonRepository personRepository;

  public List<PayPalDTO> listAll() {
    List<PayPalEntity> payPalEntities = payPalRepository.findAll();
    return payPalEntities.stream().map(PayPalDTO::new).toList();
  }

  public void insert(PayPalDTO payPalDTO) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(payPalDTO.getUsername());

    PayPalEntity payPalEntity = new PayPalEntity(payPalDTO);
    payPalEntity.setUsername(person);
    payPalEntity.setType("Chave PayPal");

    person.setPayPal(payPalEntity);

    payPalRepository.save(payPalEntity);
    personRepository.save(person);

  }

  public PayPalDTO update(PayPalDTO payPalDTO) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(payPalDTO.getUsername());
    PayPalEntity payPalEntity = payPalRepository.findByUsername(person).get();

    if(payPalDTO.getPayPal() != null) {
      payPalEntity.setPayPal(payPalDTO.getPayPal());
      person.setPayPal(payPalEntity);
    }

    personRepository.save(person);
    return new PayPalDTO(payPalRepository.save(payPalEntity));

  }

  public void delete(Long id, Long idUser) {
    PayPalEntity payPalEntity = payPalRepository.findById(id).get();
    PersonEntity person = personRepository.findById(idUser).get();

    if (!payPalEntity.getUsername().equals(idUser)) {
      throw new RuntimeException("PayPal does not belong to the specified user");
    }

    person.setPayPal(null);

    personRepository.save(person);

  }

}
