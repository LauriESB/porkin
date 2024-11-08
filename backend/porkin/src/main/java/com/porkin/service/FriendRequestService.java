package com.porkin.service;

import com.porkin.dto.FriendRequestDTO;
import com.porkin.dto.FriendshipDTO;
import com.porkin.entity.FriendRequestEntity;
import com.porkin.repository.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {

  @Autowired
  private FriendRequestRepository friendRequestRepository;

  public List<FriendRequestDTO> listAll() {
    List<FriendRequestEntity> friendRequestEntities = friendRequestRepository.findAll();
    return friendRequestEntities.stream().map(FriendRequestDTO::new).toList();
  }

  public void insert(FriendRequestDTO friendRequestDTO) {
    FriendRequestEntity friendRequestEntity = new FriendRequestEntity(friendRequestDTO);
    friendRequestRepository.save(friendRequestEntity);
  }

  public FriendRequestDTO update(FriendRequestDTO friendRequestDTO) {
    FriendRequestEntity friendRequestEntity = new FriendRequestEntity(friendRequestDTO);
    return new FriendRequestDTO(friendRequestRepository.save(friendRequestEntity));
  }

  public void delete(Long id) {
    FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id).get();
    friendRequestRepository.delete(friendRequestEntity);
  }

  public FriendRequestDTO findById(Long id) {
    return new FriendRequestDTO(friendRequestRepository.findById(id).get());
  }

}
