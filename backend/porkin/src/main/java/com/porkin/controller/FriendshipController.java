package com.porkin.controller;

import com.porkin.dto.FriendshipDTO;
import com.porkin.service.FrienshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/friendship")
public class FriendshipController {

  @Autowired
  private FrienshipService friendshipService;

  @GetMapping
  public List<FriendshipDTO> listAll() {
    return friendshipService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody FriendshipDTO friendshipDTO) {
    friendshipService.insert(friendshipDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    friendshipService.delete(id);
    return ResponseEntity.ok().build();
  }

}
