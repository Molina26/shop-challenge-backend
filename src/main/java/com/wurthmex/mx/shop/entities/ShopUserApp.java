package com.wurthmex.mx.shop.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "password")
})
public class ShopUserApp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", length = 150, nullable = false)
  private String username;

  @Column(name = "password", columnDefinition = "TEXT", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "is_available", length = 150, nullable = false)
  private Boolean isAvailable;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean isIsAvailable() {
    return this.isAvailable;
  }

  public Boolean getIsAvailable() {
    return this.isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public Set<Role> getRoles() {
    return this.roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", username='" + getUsername() + "'" +
        ", password='" + getPassword() + "'" +
        ", email='" + getEmail() + "'" +
        ", isAvailable='" + isIsAvailable() + "'" +
        ", roles='" + getRoles() + "'" +
        "}";
  }

}
