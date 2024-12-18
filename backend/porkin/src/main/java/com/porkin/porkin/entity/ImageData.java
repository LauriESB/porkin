package com.porkin.porkin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "imageData")
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

  public ImageData() {}

  private ImageData(String name, String type, byte[] imageData, PersonEntity user) {
    this.name = name;
    this.type = type;
    this.imageData = imageData;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public byte[] getImageData() {
    return imageData;
  }

  public PersonEntity getUser() {
    return user;
  }

  public static class Builder {
    private String name;
    private String type;
    private byte[] imageData;
    private PersonEntity user;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder imageData(byte[] imageData) {
      this.imageData = imageData;
      return this;
    }

    public Builder user(PersonEntity user) {
      this.user = user;
      return this;
    }

    public ImageData build() {
      return new ImageData(name, type, imageData, user);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

}
