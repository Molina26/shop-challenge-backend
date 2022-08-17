package com.wurthmex.mx.shop.services;

import java.util.List;

import com.wurthmex.mx.shop.entities.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wurthmex.mx.shop.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Role> findRoles() {
    return roleRepository.findAll();
  }

}
