package com.porkin.paymentMethods.service;

import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.dto.PayPalDTO;
import com.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.paymentMethods.repository.PayPalRepository;
import com.porkin.repository.PersonRepository;
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
    PersonEntity person = personRepository.findByUsername(payPalDTO.getUsername()).get();

    PayPalEntity payPalEntity = new PayPalEntity(payPalDTO);

    payPalEntity.setIdUser(person);
    payPalEntity.setType("Chave PayPal");

    person.setPaypal(payPalEntity);

    payPalRepository.save(payPalEntity);
    personRepository.save(person);

  }

  public PayPalDTO update(PayPalDTO payPalDTO) {
    PersonEntity person = personRepository.findByUsername(payPalDTO.getUsername()).get();
    PayPalEntity payPalEntity = payPalRepository.findByIdUser(person).get();

    if(payPalDTO.getPayPalKey() != null) {
      payPalEntity.setPayPalKey(payPalDTO.getPayPalKey());
      person.setPaypal(payPalEntity);
    }

    personRepository.save(person);
    return new PayPalDTO(payPalRepository.save(payPalEntity));

  }

  public void delete(Long id, Long idUser) {
    PayPalEntity payPalEntity = payPalRepository.findById(id).get();
    PersonEntity person = personRepository.findById(idUser).get();

    if (!payPalEntity.getIdUser().equals(idUser)) {
      throw new RuntimeException("PayPal does not belong to the specified user");
    }

    person.setPaypal(null);

    personRepository.save(person);

  }

}
