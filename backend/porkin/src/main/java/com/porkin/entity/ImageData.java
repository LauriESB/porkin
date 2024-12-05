package com.porkin.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imageData")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ImageData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String type;

  @Lob
  @Column(name = "imageData", length = 1000)
  private byte[] imageData;

  @OneToOne
  @JoinColumn
  private PersonEntity user;

}
