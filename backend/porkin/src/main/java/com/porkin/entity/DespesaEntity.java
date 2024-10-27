package com.porkin.entity;

import com.porkin.dto.DespesaDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "despesa")
public class DespesaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private int valorTotal;

  @Column(nullable = false)
  private String titulo;

  @Column(nullable = false)
  private LocalDate data_criacao;

  @Column(nullable = false)
  private LocalDate dataLimitePgto;

  @Column(nullable = false)
  private boolean situacao;

  @ManyToOne
  @JoinColumn(name = "usuarioCriador")
  private PessoaEntity usuarioCriador;

  // despesEntity constructors

  public DespesaEntity(DespesaDTO despesaDTO) {
    BeanUtils.copyProperties(despesaDTO, this);
  }

  public DespesaEntity() {

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
    return data_criacao;
  }

  public void setData_criacao(LocalDate data_criacao) {
    this.data_criacao = data_criacao;
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

  public PessoaEntity getUsuarioCriador() {
    return usuarioCriador;
  }

  public void setUsuarioCriador(PessoaEntity usuarioCriador) {
    this.usuarioCriador = usuarioCriador;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DespesaEntity that = (DespesaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
