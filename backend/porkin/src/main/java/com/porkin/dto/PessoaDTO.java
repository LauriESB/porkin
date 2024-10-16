package com.porkin.dto;


import com.porkin.entity.PessoaEntity;
import org.springframework.beans.BeanUtils;

public class PessoaDTO {

  private Long id;

  private String nome;

  private String email;

  private int senha;

  private int celular;

  // PessoaDTO constructors

  public PessoaDTO(PessoaEntity pessoaEntity) {
    BeanUtils.copyProperties(pessoaEntity, this);
  }

  public PessoaDTO() {

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

}
