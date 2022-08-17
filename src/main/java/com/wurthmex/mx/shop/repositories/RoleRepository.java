package com.wurthmex.mx.shop.repositories;

import java.util.Optional;

import com.wurthmex.mx.shop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
  Optional<Role> findByName(String name);
}
