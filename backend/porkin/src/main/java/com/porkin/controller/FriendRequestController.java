package com.porkin.controller;

import com.porkin.dto.ExpenseSplitDTO;
import com.porkin.dto.FriendRequestDTO;
import com.porkin.dto.FriendshipDTO;
import com.porkin.dto.PersonDTO;
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
/*
  @PostMapping("/{requestId}/accept")
  public ResponseEntity<String> acceptRequest(@PathVariable Long requestId) {
    friendRequestService.acceptRequest(requestId);
    return ResponseEntity.ok("Friendship accepted");
  }

  @PostMapping("/{requestId}/reject")
  public ResponseEntity<String> rejectRequest(@PathVariable Long requestId) {
    friendRequestService.rejectRequest(requestId);
    return ResponseEntity.ok("Friendship rejected");
  }
*/
}
