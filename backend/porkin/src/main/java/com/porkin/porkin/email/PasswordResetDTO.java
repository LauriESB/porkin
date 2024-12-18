package com.porkin.porkin.email;

public class PasswordResetDTO {

  private String email;

  public PasswordResetDTO(String email, String token, String newPassword) {
    this.email = email;
  }

  public PasswordResetDTO() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
