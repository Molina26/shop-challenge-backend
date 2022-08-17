package com.example.springresttemplate.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springresttemplate.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
  Optional<Role> findByName(String name);
}
