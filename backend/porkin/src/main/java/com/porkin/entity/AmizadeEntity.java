package com.porkin.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "amizade")
public class AmizadeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUsuario;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAmigo;

  @ManyToMany
  @JoinColumn(name = "idUsuario")
  private PessoaEntity fkPessoaUsuario;

  @ManyToMany
  @JoinColumn(name = "idAmigo")
  private PessoaEntity fkPessoaAmigo;

  // pessoaEntity constructors

  public AmizadeEntity() {

  }

  // getters and setters

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Long getIdAmigo() {
    return idAmigo;
  }

  public void setIdAmigo(Long idAmigo) {
    this.idAmigo = idAmigo;
  }

  public PessoaEntity getFkPessoaUsuario() {
    return fkPessoaUsuario;
  }

  public void setFkPessoaUsuario(PessoaEntity fkPessoaUsuario) {
    this.fkPessoaUsuario = fkPessoaUsuario;
  }

  public PessoaEntity getFkPessoaAmigo() {
    return fkPessoaAmigo;
  }

  public void setFkPessoaAmigo(PessoaEntity fkPessoaAmigo) {
    this.fkPessoaAmigo = fkPessoaAmigo;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AmizadeEntity that = (AmizadeEntity) o;
    return Objects.equals(idUsuario, that.idUsuario);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(idUsuario);
  }

}
