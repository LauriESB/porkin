package com.porkin.service;

import com.porkin.dto.FriendshipDTO;
import com.porkin.entity.FriendshipEntity;
import com.porkin.entity.PersonEntity;
import com.porkin.repository.FriendshipRepository;
import com.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService { // service chama o repository

  @Autowired
  private FriendshipRepository friendshipRepository;

  @Autowired
  private PersonRepository personRepository;

  // busca dados no entity e retorna dto
  public List<FriendshipDTO> listAll() {
    List<FriendshipEntity> friendshipEntities = friendshipRepository.findAll(); // usa usuário repository pra buscar nas entities
    return friendshipEntities.stream().map(FriendshipDTO::new).toList();
  }

  public void insert(FriendshipDTO friendshipDTO) {
    PersonEntity idUser = personRepository.findById(friendshipDTO.getIdUser()).get();
    PersonEntity idFriend = personRepository.findById(friendshipDTO.getIdFriend()).get();

    FriendshipEntity friendshipEntity = new FriendshipEntity(friendshipDTO, idUser, idFriend);
    friendshipRepository.save(friendshipEntity);
  }
  
  public void delete(Long id) {
    FriendshipEntity friendshipEntity = friendshipRepository.findById(id).get();
    friendshipRepository.delete(friendshipEntity);
  }

  public FriendshipDTO findById(Long id) {
    return new FriendshipDTO(friendshipRepository.findById(id).get());
  }

}
