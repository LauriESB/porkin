package com.porkin.controller;

import com.porkin.dto.FriendRequestDTO;
import com.porkin.dto.FriendshipDTO;
import com.porkin.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/friendRequest")
public class FriendRequestController {

  @Autowired
  private FriendRequestService friendRequestService;

  @GetMapping
  public List<FriendRequestDTO> listAll() {
    return friendRequestService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody FriendRequestDTO friendRequestDTO) {
    friendRequestService.insert(friendRequestDTO);
  }

  @PutMapping
  public FriendRequestDTO update(@RequestBody FriendRequestDTO friendRequestDTO) {
    return friendRequestService.update(friendRequestDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    friendRequestService.delete(id);
    return ResponseEntity.ok().build();
  }

}
