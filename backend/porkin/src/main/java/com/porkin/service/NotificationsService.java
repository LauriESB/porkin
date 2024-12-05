package com.porkin.service;

import com.porkin.dto.FriendshipDTO;
import com.porkin.dto.NotificationsDTO;
import com.porkin.entity.NotificationsEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.NotificationsRepository;
import com.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {

  @Autowired
  private NotificationsRepository notificationsRepository;

  @Autowired
  private PersonRepository personRepository;

  public List<NotificationsDTO> listAll(String username) {

    PersonEntity person = personRepository.findByUsername(username).get();

    List<NotificationsEntity> notificationsEntities = notificationsRepository.findByPerson(person).stream().toList();
    return notificationsEntities.stream().map(NotificationsDTO::new).toList();
  }

  public void delete(Long id) {
    NotificationsEntity notificationsEntity = notificationsRepository.findById(id).get();
    notificationsRepository.delete(notificationsEntity);
  }

}
