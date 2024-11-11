package com.porkin.service;

import com.porkin.dto.FriendRequestDTO;
import com.porkin.dto.FriendshipDTO;
import com.porkin.dto.PersonDTO;
import com.porkin.entity.FriendRequestEntity;
import com.porkin.entity.FriendshipEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.FriendRequestRepository;
import com.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FriendRequestService {

  @Autowired
  private FriendRequestRepository friendRequestRepository;

  @Autowired
  private PersonRepository personRepository;

  public List<FriendRequestDTO> listAll() {
    List<FriendRequestEntity> friendRequestEntities = friendRequestRepository.findAll();
    return friendRequestEntities.stream().map(FriendRequestDTO::new).toList();
  }

  public void insert(FriendRequestDTO requestFriendship) {
    FriendRequestEntity friendRequestEntity = new FriendRequestEntity(requestFriendship);

    PersonEntity personRequester = personRepository.findById(friendRequestEntity.getPersonRequester()).get();
    PersonEntity personReceiver = personRepository.findById(friendRequestEntity.getPersonReceiver()).get();

    FriendRequestEntity newData = new FriendRequestEntity();
    // saving registry in the notifications table.

    newData.setPersonRequester(personRequester.getId());
    newData.setPersonReceiver(personReceiver.getId());
    newData.setMessage(personRequester.getName() + " wants to add you as a friend");
    newData.setCreationDate(LocalDateTime.now());
    newData.setStatus("on hold");

    friendRequestRepository.save(newData);
  }

  public void acceptRequest(FriendRequestDTO requestFriendship) {

  }

  public void rejectRequest(Long id) {
    FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();

    friendRequestEntity.setStatus("rejected");
    friendRequestRepository.save(friendRequestEntity);
  }

  public FriendRequestDTO findById(Long id) {
    return new FriendRequestDTO(friendRequestRepository.findById(id).get());
  }

}
