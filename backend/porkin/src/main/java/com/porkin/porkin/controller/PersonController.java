package com.porkin.porkin.controller;

import com.porkin.porkin.dto.PersonDTO;
import com.porkin.porkin.email.PasswordResetDTO;
import com.porkin.porkin.email.PasswordValidateDTO;
import com.porkin.porkin.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @Autowired
  private AuthControler authControler;

  @PostMapping("/recovery")
  public ResponseEntity<String> sendRecoveryCode(@RequestBody PasswordResetDTO request) {
    personService.sendRecoveryCode(request.getEmail());
    return ResponseEntity.ok("Código de recuperação enviado");
  }

  @PostMapping("/reset")
  public ResponseEntity<String> resetPassword(@RequestBody PasswordValidateDTO request) {
    personService.resetPassword(request.getEmail(), request.getRecoveryCode(), request.getNewPassword());
    return ResponseEntity.ok("Senha alterada com sucesso");
  }

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
    authControler.register(personDTO);
  }

  @PutMapping("/{username}")
  public PersonDTO update(@PathVariable("username") String username, @RequestBody PersonDTO personDTO) {
    return personService.update(username, personDTO);
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<Void> delete(@PathVariable("username") String username) {
    personService.delete(username);
    return ResponseEntity.ok().build();
  }

}
