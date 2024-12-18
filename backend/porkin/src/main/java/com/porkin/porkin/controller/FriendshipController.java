package com.porkin.porkin.controller;

import com.porkin.porkin.dto.FriendshipDTO;
import com.porkin.porkin.service.FriendshipService;
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

  @GetMapping
  public List<FriendshipDTO> listAll() {
    return friendshipService.listAll();
  }

  @GetMapping("/{username}")
  public ResponseEntity<Set<String>> getFriends(@PathVariable String username) {
    Set<String> friendIds = friendshipService.getFriendIds(username);
    return ResponseEntity.ok(friendIds);
  }

  @DeleteMapping("/{user}/{friend}")
  public ResponseEntity<Void> delete(@PathVariable("user") String user, @PathVariable String friend) {
    friendshipService.delete(user, friend);
    return ResponseEntity.ok().build();
  }

}
