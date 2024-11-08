package com.porkin.service;

import com.porkin.dto.FriendshipDTO;
import com.porkin.dto.NotificationsDTO;
import com.porkin.entity.NotificationsEntity;
import com.porkin.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {

  @Autowired
  private NotificationsRepository notificationsRepository;

  public List<NotificationsDTO> listAll() {
    List<NotificationsEntity> notificationsEntities = notificationsRepository.findAll();
    return notificationsEntities.stream().map(NotificationsDTO::new).toList();
  }

  public void delete(Long id) {
    NotificationsEntity notificationsEntity = notificationsRepository.findById(id).get();
    notificationsRepository.delete(notificationsEntity);
  }

  //public NotificationsDTO findById(Long id) {
  //  return new NotificationsDTO(notificationsRepository.findById(id).get());
  //}

}
