package com.wurthmex.mx.shop.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 30)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<ShopUserApp> users;


  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<ShopUserApp> getUsers() {
    return this.users;
  }

  public void setUsers(Set<ShopUserApp> users) {
    this.users = users;
  }

}
