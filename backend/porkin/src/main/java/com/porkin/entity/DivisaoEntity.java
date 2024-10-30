package com.porkin.entity;

import com.porkin.compositekeys.DivisaoIDs;
import com.porkin.dto.DivisaoDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Entity
@Table(name = "divisao")
public class DivisaoEntity {

  @EmbeddedId
  private DivisaoIDs divisaoIDs;

  private boolean pago;

  @ManyToOne
  @JoinColumn(name = "idUsuarioCriador")
  private PessoaEntity idUsuarioCriador;

  // divisaoEntity constructors

  public DivisaoEntity(DivisaoDTO divisaoDTO) {
    BeanUtils.copyProperties(divisaoDTO, this);
  }

  public DivisaoEntity() {

  }

  // getters and setters


  public DivisaoIDs getDivisaoIDs() {
    return divisaoIDs;
  }

  public void setDivisaoIDs(DivisaoIDs divisaoIDs) {
    this.divisaoIDs = divisaoIDs;
  }

  public PessoaEntity getIdUsuarioCriador() {
    return idUsuarioCriador;
  }

  public void setIdUsuarioCriador(PessoaEntity idUsuarioCriador) {
    this.idUsuarioCriador = idUsuarioCriador;
  }

  public boolean isPago() {
    return pago;
  }

  public void setPago(boolean pago) {
    this.pago = pago;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DivisaoEntity that = (DivisaoEntity) o;
    return Objects.equals(divisaoIDs, that.divisaoIDs) && Objects.equals(idUsuarioCriador, that.idUsuarioCriador);
  }

  @Override
  public int hashCode() {
    return Objects.hash(divisaoIDs, idUsuarioCriador);
  }

}
