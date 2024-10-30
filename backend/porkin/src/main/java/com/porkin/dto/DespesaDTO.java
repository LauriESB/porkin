package com.porkin.dto;

import com.porkin.entity.DespesaEntity;
import com.porkin.entity.PessoaEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class DespesaDTO {

  private Long id;

  private int valorTotal;

  private String titulo;

  private LocalDate dataCriacao;

  private LocalDate dataLimitePgto;

  private boolean situacao;

  private Long usuarioCriador;

  // DespesaDTO constructor

  public DespesaDTO(DespesaEntity despesaEntity) {
    BeanUtils.copyProperties(despesaEntity, this);
  }

  public DespesaDTO() {

  }

  // getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(int valorTotal) {
    this.valorTotal = valorTotal;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public LocalDate getData_criacao() {
    return dataCriacao;
  }

  public void setData_criacao(LocalDate data_criacao) {
    this.dataCriacao = data_criacao;
  }

  public LocalDate getDataLimitePgto() {
    return dataLimitePgto;
  }

  public void setDataLimitePgto(LocalDate dataLimitePgto) {
    this.dataLimitePgto = dataLimitePgto;
  }

  public boolean isSituacao() {
    return situacao;
  }

  public void setSituacao(boolean situacao) {
    this.situacao = situacao;
  }

  public Long getUsuarioCriador() {
    return usuarioCriador;
  }

  public void setUsuarioCriador(Long usuarioCriador) {
    this.usuarioCriador = usuarioCriador;
  }

}
