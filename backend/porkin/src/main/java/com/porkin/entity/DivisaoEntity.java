package com.porkin.entity;

import com.porkin.dto.DivisaoDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class DivisaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAmigo;

  private int porcentagemDespesa;

  private boolean pago;

  // divisaoEntity constructors

  public DivisaoEntity(DivisaoDTO divisaoDTO) {
    BeanUtils.copyProperties(divisaoDTO, this);
  }

  public DivisaoEntity() {

  }

  // getters and setters

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getIdAmigo() {
    return idAmigo;
  }

  public void setIdAmigo(Long idAmigo) {
    this.idAmigo = idAmigo;
  }

  public int getPorcentagemDespesa() {
    return porcentagemDespesa;
  }

  public void setPorcentagemDespesa(int porcentagemDespesa) {
    this.porcentagemDespesa = porcentagemDespesa;
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
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
