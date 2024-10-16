package com.porkin.dto;

import com.porkin.entity.AmizadeEntity;
import com.porkin.entity.PessoaEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

public class AmizadeDTO {

  private Long idUsuario;

  private Long idAmigo;

  private PessoaEntity fkPessoaUsuario;

  private PessoaEntity fkPessoaAmigo;

  // AmizadeDTO constructors

  public AmizadeDTO(AmizadeEntity amizadeEntity) {
    BeanUtils.copyProperties(amizadeEntity, this);
  }

  public AmizadeDTO() {

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

}
