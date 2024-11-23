package com.porkin.controller;

import com.porkin.dto.PersonDTO;
import com.porkin.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping
  public List<PersonDTO> listAll() {
    return personService.listAll();
  }

  @GetMapping("/{username}")
  public PersonDTO listAll(@PathVariable("username") String username) {
    return personService.findByUsername(username);
  }

  @PostMapping
  public void insert(@RequestBody PersonDTO personDTO) {
    personService.insert(personDTO);
  }

  @PutMapping("/{id}")
  public PersonDTO update(@PathVariable("id") Long id, @RequestBody PersonDTO personDTO) {
    return personService.update(id, personDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    personService.delete(id);
    return ResponseEntity.ok().build();
  }

}
