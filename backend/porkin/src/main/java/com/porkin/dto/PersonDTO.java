package com.porkin.dto;

import com.porkin.entity.PersonEntity;
import com.porkin.paymentMethods.dto.PixDTO;
import org.springframework.beans.BeanUtils;

public class PersonDTO {

  private Long id;

  private String name;

  private String username;

  private String email;

  private String password;

  private PixDTO pix;

  // constructors

  public PersonDTO(PersonEntity personEntity) {
    BeanUtils.copyProperties(personEntity, this);
  }

  public PersonDTO() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public PixDTO getPix() {
    return pix;
  }

  public void setPix(PixDTO pix) {
    this.pix = pix;
  }

}
