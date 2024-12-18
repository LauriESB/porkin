package com.porkin.porkin.service;

import com.porkin.porkin.dto.FriendshipDTO;
import com.porkin.porkin.entity.FriendshipEntity;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.repository.FriendshipRepository;
import com.porkin.porkin.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FriendshipService {

  @Autowired
  private FriendshipRepository friendshipRepository;

  @Autowired
  private PersonRepository personRepository;

  public List<FriendshipDTO> listAll() {
    List<FriendshipEntity> friendshipEntities = friendshipRepository.findAll();
    return friendshipEntities.stream().map(FriendshipDTO::new).toList();
  }

  public Set<String> getFriendIds(String username) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(username);
    return person.getFriendsUsernames();
  }

  @Transactional
  public void delete(String user, String friend) {

    PersonEntity personUser = (PersonEntity) personRepository.findByUsername(user);
    PersonEntity personFriend = (PersonEntity) personRepository.findByUsername(friend);

    personUser.getFriendsUsernames().remove(friend);
    personFriend.getFriendsUsernames().remove(user);

    personRepository.save(personUser);
    personRepository.save(personFriend);

    friendshipRepository.deleteByUserAndFriend(user, friend);
  }

  public FriendshipDTO findById(Long id) {
    return new FriendshipDTO(friendshipRepository.findById(id).get());
  }

}
