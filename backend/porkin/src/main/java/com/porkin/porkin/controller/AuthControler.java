package com.porkin.porkin.controller;

import com.porkin.porkin.dto.AuthDTO;
import com.porkin.porkin.dto.LoginResponseDTO;
import com.porkin.porkin.dto.PersonDTO;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.repository.PersonRepository;
import com.porkin.porkin.roles.PersonRoles;
import com.porkin.porkin.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthControler {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((PersonEntity) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid PersonDTO personDTO) {
    if(this.personRepository.findByUsername(personDTO.getUsername()) != null) return ResponseEntity.badRequest().build();
    String encryptedPasswrod = new BCryptPasswordEncoder().encode(personDTO.getPassword());
    PersonEntity personEntity = new PersonEntity(personDTO.getName(), personDTO.getUsername(), personDTO.getEmail(), encryptedPasswrod, personDTO.getRole());

    personEntity.setRole(PersonRoles.USER);

    this.personRepository.save(personEntity);
    return ResponseEntity.ok().build();

  }

}
