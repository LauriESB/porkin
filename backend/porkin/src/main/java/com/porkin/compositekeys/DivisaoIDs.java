package com.porkin.compositekeys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DivisaoIDs implements Serializable {

  private Long idDespesa;
  private Long idAmigo;

  public DivisaoIDs() {

  }

  public DivisaoIDs(Long idDespesa, Long idAmigo) {
    this.idDespesa = idDespesa;
    this.idAmigo = idAmigo;
  }

  public Long getIdDespesa() {
    return idDespesa;
  }

  public void setIdDespesa(Long idDespesa) {
    this.idDespesa = idDespesa;
  }

  public Long getIdAmigo() {
    return idAmigo;
  }

  public void setIdAmigo(Long idAmigo) {
    this.idAmigo = idAmigo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DivisaoIDs that = (DivisaoIDs) o;
    return Objects.equals(idDespesa, that.idDespesa) && Objects.equals(idAmigo, that.idAmigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDespesa, idAmigo);
  }

}
