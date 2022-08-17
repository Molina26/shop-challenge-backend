package com.wurthmex.mx.shop.services;

import java.util.Optional;

import com.wurthmex.mx.shop.payload.acount.request.UserSingupRequestDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.wurthmex.mx.shop.entities.ShopUserApp;

public interface ShopUserAppService extends UserDetailsService{
  UserDetails loadUserByUsername(String username);

  ShopUserApp findUserByUsername(String username);

  Optional<ShopUserApp> findUserByEmail(String email);

  ShopUserApp createUser(UserSingupRequestDto userDto);
}
