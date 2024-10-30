package com.porkin.compositekeys;

import java.io.Serializable;

public class AmizadeIDs implements Serializable {

  private Long idUsuario;
  private Long idAmigo;

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

}
