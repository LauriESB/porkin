package com.porkin.porkin.roles;

public enum PersonRoles {

  ADMIN("admin"),
  USER("user");

  private String role;

  PersonRoles(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
