package com.porkin.porkin.controller;

import com.porkin.porkin.dto.FriendRequestDTO;
import com.porkin.porkin.service.FriendRequestService;
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

  @PostMapping("/accept/{user}/{friend}")
  public ResponseEntity<String> acceptFriendRequest(@PathVariable String user, @PathVariable String friend) {
    friendRequestService.acceptRequest(user, friend);
    return ResponseEntity.ok("Amizade aceita!");
  }

  @PostMapping("/reject/{user}/{friend}")
  public ResponseEntity<String> rejectRequest(@PathVariable String user, @PathVariable String friend) {
    friendRequestService.rejectRequest(user, friend);
    return ResponseEntity.ok("Amizade negada!");
  }

}
