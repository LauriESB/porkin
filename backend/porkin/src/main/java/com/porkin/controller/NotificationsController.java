package com.porkin.controller;


import com.porkin.dto.NotificationsDTO;
import com.porkin.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationsController {

  @Autowired
  private NotificationsService notificationsService;

  @GetMapping
  public List<NotificationsDTO> listAll() {
    return notificationsService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody NotificationsDTO notificationsDTO) {
    notificationsService.insert(notificationsDTO);
  }

  @PutMapping
  public NotificationsDTO update(@RequestBody NotificationsDTO notificationsDTO) {
    return notificationsService.update(notificationsDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable("id") Long id) {
    notificationsService.delete(id);
    return ResponseEntity.ok().build();
  }

}
