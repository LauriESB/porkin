package com.porkin.controller;

import com.porkin.dto.FriendRequestDTO;
import com.porkin.dto.FriendshipDTO;
import com.porkin.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/friendship")
public class FriendshipController {

  @Autowired
  private FriendshipService friendshipService;

  @GetMapping("/friends/{userId}")
  public ResponseEntity<Set<Long>> getFriends(@PathVariable Long userId) {
    Set<Long> friendIds = friendshipService.getFriendIds(userId);
    return ResponseEntity.ok(friendIds);
  }

  /*
  @GetMapping("/friends/{userId}")
  public List<FriendshipDTO> listAll(@PathVariable Long userId) {
    return friendshipService.listAll(userId);
  }
  */

  /*
  @PostMapping
  public void insert(@RequestBody FriendshipDTO friendshipDTO) {
    friendshipService.insert(friendshipDTO);
  }
  */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    friendshipService.delete(id);
    return ResponseEntity.ok().build();
  }

}
