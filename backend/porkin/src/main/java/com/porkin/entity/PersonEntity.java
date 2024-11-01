package com.porkin.entity;

import com.porkin.dto.PersonDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "person")
public class PersonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUser;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private int password;

  @Column(nullable = false)
  private int mobileNumber;

  // pessoaEntity constructors

  public PersonEntity(PersonDTO personDTO) {
    BeanUtils.copyProperties(personDTO, this);
  }

  public PersonEntity() {

  }

  // getters and setters

  public Long getId() {
    return idUser;
  }

  public void setId(Long id) {
    this.idUser = id;
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

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonEntity that = (PersonEntity) o;
    return Objects.equals(idUser, that.idUser);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(idUser);
  }

}
