package com.porkin.dto;

import com.porkin.entity.DivisaoEntity;
import org.springframework.beans.BeanUtils;

public class DivisaoDTO {

  private long id;

  private Long idAmigo;

  private int porcentagemDespesa;

  private boolean pago;

  // DivisaoDTO constructors

  public DivisaoDTO(DivisaoEntity divisaoEntity) {
    BeanUtils.copyProperties(divisaoEntity, this);
  }

  public DivisaoDTO() {

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

}
