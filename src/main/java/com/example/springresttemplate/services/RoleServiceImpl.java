package com.example.springresttemplate.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springresttemplate.entities.Role;
import com.example.springresttemplate.repositories.RoleRepository;

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
