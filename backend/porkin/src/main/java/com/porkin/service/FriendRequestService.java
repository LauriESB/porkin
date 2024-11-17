package com.porkin.service;

import com.porkin.compositekeys.FriendshipIDs;
import com.porkin.controller.FriendshipController;
import com.porkin.dto.FriendRequestDTO;
import com.porkin.dto.FriendshipDTO;
import com.porkin.dto.PersonDTO;
import com.porkin.entity.FriendRequestEntity;
import com.porkin.entity.FriendshipEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.FriendRequestRepository;
import com.porkin.repository.FriendshipRepository;
import com.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FriendRequestService {

  @Autowired
  private FriendRequestRepository friendRequestRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private FriendshipRepository friendshipRepository;

  @Autowired
  private FriendshipController newFriendship;

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

  @Transactional
  public void acceptRequest(Long id) {
    FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
    friendRequestRepository.delete(friendRequestEntity);

    PersonEntity personUser = personRepository.findById(friendRequestEntity.getPersonReceiver()).get();
    PersonEntity personFriend = personRepository.findById(friendRequestEntity.getPersonRequester()).get();

    FriendshipEntity friendshipEntity = new FriendshipEntity();
    friendshipEntity.setFriendshipIDs(new FriendshipIDs(friendRequestEntity.getPersonReceiver(), friendRequestEntity.getPersonRequester()));
    friendshipEntity.setFkPersonUser(personUser);
    friendshipEntity.setFkPersonFriend(personFriend);
    friendshipRepository.save(friendshipEntity); // aqui eu crio a entrada em friendship repository


    // depois eu preciso ATUALIZAR od IDs dos amigos na lista de amigos do perfil do usuário!
    personUser.getFriendIDs().add(personFriend.getId());
    personFriend.getFriendIDs().add(personUser.getId());

    // salvo tudo
    personRepository.save(personUser);
    personRepository.save(personFriend);

    /*
    FriendshipEntity reciprocalFriendship = new FriendshipEntity();
    reciprocalFriendship.setFriendshipIDs(new FriendshipIDs(personFriend.getIdUser(), personUser.getIdUser()));
    reciprocalFriendship.setFkPersonUser(personFriend);
    reciprocalFriendship.setFkPersonFriend(personUser);

    friendshipRepository.save(reciprocalFriendship);

    */
    //friendshipRepository.flush();
  }

  public void rejectRequest(Long id) {
    FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
    friendRequestRepository.delete(friendRequestEntity);
  }

  public FriendRequestDTO findById(Long id) {
    return new FriendRequestDTO(friendRequestRepository.findById(id).get());
  }

}
