package com.example.springresttemplate.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springresttemplate.entities.UserApp;
import com.example.springresttemplate.payload.acount.request.UserSingupRequestDto;

public interface UserAppService extends UserDetailsService{
  UserDetails loadUserByUsername(String username);

  UserApp findUserByUsername(String username);

  Optional<UserApp> findUserByEmail(String email);

  UserApp createUser(UserSingupRequestDto userDto);
}
