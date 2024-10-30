package com.porkin.entity;

import com.porkin.compositekeys.AmizadeIDs;
import com.porkin.dto.AmizadeDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "amizade")
public class AmizadeEntity {

  @EmbeddedId
  private AmizadeIDs amizadeIDs;

  @ManyToOne
  @MapsId("idUsuario")
  @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
  private PessoaEntity fkPessoaUsuario;

  @ManyToOne
  @MapsId("idAmigo")
  @JoinColumn(name = "idAmigo", referencedColumnName = "idUsuario")
  private PessoaEntity fkPessoaAmigo;

  // pessoaEntity constructors

  public AmizadeEntity(AmizadeDTO amizadeDTO) {
    BeanUtils.copyProperties(amizadeDTO, this);
  }

  public AmizadeEntity() {

  }

  // getters and setters

  public Long getIdUsuario() {
    return amizadeIDs.getIdUsuario();
  }

  public void setIdUsuario(Long idUsuario) {
    amizadeIDs.setIdUsuario(idUsuario);
  }

  public Long getIdAmigo() {
    return amizadeIDs.getIdAmigo();
  }

  public void setIdAmigo(Long idAmigo) {
    amizadeIDs.setIdAmigo(idAmigo);
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
    return Objects.equals(amizadeIDs.getIdUsuario(), that.amizadeIDs.getIdUsuario());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(amizadeIDs.getIdUsuario());
  }

}
