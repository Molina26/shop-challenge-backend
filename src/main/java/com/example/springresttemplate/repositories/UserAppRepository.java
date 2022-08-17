package com.example.springresttemplate.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springresttemplate.entities.UserApp;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
  Optional<UserApp> findByUsername(String username);

  Optional<UserApp> findByEmail(String email);
}
