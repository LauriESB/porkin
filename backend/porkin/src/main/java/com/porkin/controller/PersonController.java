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

  @PostMapping
  public void insert(@RequestBody PersonDTO personDTO) {
    personService.insert(personDTO);
  }

  @PutMapping
  public PersonDTO update(@RequestBody PersonDTO personDTO) {
    return personService.update(personDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    personService.delete(id);
    return ResponseEntity.ok().build();
  }

}
