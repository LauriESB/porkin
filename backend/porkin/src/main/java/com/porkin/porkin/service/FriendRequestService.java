package com.porkin.porkin.service;

import com.porkin.porkin.dto.FriendRequestDTO;
import com.porkin.porkin.entity.FriendRequestEntity;
import com.porkin.porkin.entity.FriendshipEntity;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.repository.FriendRequestRepository;
import com.porkin.porkin.repository.FriendshipRepository;
import com.porkin.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FriendRequestService {

  @Autowired
  private FriendRequestRepository friendRequestRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private FriendshipRepository friendshipRepository;

  public List<FriendRequestDTO> listAll() {
    List<FriendRequestEntity> friendRequestEntities = friendRequestRepository.findAll();
    return friendRequestEntities.stream().map(FriendRequestDTO::new).toList();
  }

  public void insert(FriendRequestDTO requestFriendship) {
    FriendRequestEntity friendRequestEntity = new FriendRequestEntity(requestFriendship);

    PersonEntity personRequester = (PersonEntity) personRepository.findByUsername(friendRequestEntity.getPersonRequester());
    PersonEntity personReceiver = (PersonEntity) personRepository.findByUsername(friendRequestEntity.getPersonReceiver());

    FriendRequestEntity newData = new FriendRequestEntity();
    // saving registry in the notifications table.

    newData.setPersonRequester(personRequester.getUsername());
    newData.setPersonReceiver(personReceiver.getUsername());
    newData.setMessage(personRequester.getUsername() + " deseja te adicionar como amigo");
    newData.setCreationDate(LocalDateTime.now());
    newData.setStatus("em espera");

    friendRequestRepository.save(newData);
  }

  @Transactional
  public void acceptRequest(String user, String friend) {
    FriendRequestEntity friendRequestEntity = friendRequestRepository.findRequestByPersonReceiverAndPersonRequester(user, friend).get();

    PersonEntity personUser = (PersonEntity) personRepository.findByUsername(friendRequestEntity.getPersonReceiver());
    PersonEntity personFriend = (PersonEntity) personRepository.findByUsername(friendRequestEntity.getPersonRequester());

    FriendshipEntity friendshipEntity = new FriendshipEntity();
    //friendshipEntity.setFriendshipIDs(new FriendshipIDs(friendRequestEntity.getPersonReceiver(), friendRequestEntity.getPersonRequester()));
    friendshipEntity.setFkPersonUser(personUser);
    friendshipEntity.setFkPersonFriend(personFriend);
    friendshipRepository.save(friendshipEntity); // aqui eu crio a entrada em friendship repository

    // depois eu preciso ATUALIZAR od IDs dos amigos na lista de amigos do perfil do usuário!
    personUser.getFriendsUsernames().add(personFriend.getUsername());
    personFriend.getFriendsUsernames().add(personUser.getUsername());

    personUser.getFriendships().add(friendshipEntity);

    // salvo tudo
    personRepository.save(personUser);
    personRepository.save(personFriend);
    friendRequestRepository.delete(friendRequestEntity);

  }

  public void rejectRequest(String user, String friend) {

    System.out.println("Rejecting friend request for user: " + user + ", friend: " + friend);

    FriendRequestEntity friendRequestEntity = friendRequestRepository.findRequestByPersonReceiverAndPersonRequester(user, friend).get();
    friendRequestRepository.delete(friendRequestEntity);
  }

  public FriendRequestDTO findById(Long id) {
    return new FriendRequestDTO(friendRequestRepository.findById(id).get());
  }

}
