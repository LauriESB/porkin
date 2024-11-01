package com.porkin.dto;


import com.porkin.entity.PersonEntity;
import org.springframework.beans.BeanUtils;

public class PersonDTO {

  private Long id;

  private String name;

  private String email;

  private int password;

  private int mobileNumber;

  // PersonDTO constructors

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

  public int getPassword() {
    return password;
  }

  public void setPassword(int password) {
    this.password = password;
  }

  public int getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(int mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

}
