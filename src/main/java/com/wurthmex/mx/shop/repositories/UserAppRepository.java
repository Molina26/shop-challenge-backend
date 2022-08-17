package com.wurthmex.mx.shop.repositories;

import java.util.Optional;

import com.wurthmex.mx.shop.entities.ShopUserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<ShopUserApp, Long> {
  Optional<ShopUserApp> findByUsername(String username);

  Optional<ShopUserApp> findByEmail(String email);
}
