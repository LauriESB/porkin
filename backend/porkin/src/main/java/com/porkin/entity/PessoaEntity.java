package com.porkin.entity;

import com.porkin.dto.PessoaDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class PessoaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private int senha;

  @Column(nullable = false)
  private int celular;

  // pessoaEntity constructors

  public PessoaEntity(PessoaDTO pessoaDTO) {
    BeanUtils.copyProperties(pessoaDTO, this);
  }

  public PessoaEntity() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getSenha() {
    return senha;
  }

  public void setSenha(int senha) {
    this.senha = senha;
  }

  public int getCelular() {
    return celular;
  }

  public void setCelular(int celular) {
    this.celular = celular;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PessoaEntity that = (PessoaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
