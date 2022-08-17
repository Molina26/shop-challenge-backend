package com.wurthmex.mx.shop.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.wurthmex.mx.shop.entities.Role;
import com.wurthmex.mx.shop.payload.acount.request.UserSingupRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wurthmex.mx.shop.entities.ShopUserApp;
import com.wurthmex.mx.shop.repositories.UserAppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopUserAppServiceImpl implements ShopUserAppService {

  private final Logger logger = LoggerFactory.getLogger(ShopUserAppServiceImpl.class);

  private final UserAppRepository userAppRepository;

  private final BCryptPasswordEncoder encoder;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {

    Optional<ShopUserApp> user = userAppRepository.findByUsername(username);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }

    ShopUserApp userToAuthenticate = user.get();

    return new User(userToAuthenticate.getUsername(), userToAuthenticate.getPassword(), new ArrayList<>());
  }

  @Override
  public ShopUserApp createUser(UserSingupRequestDto userDto) {

    ShopUserApp userCreated = null;

    try {
      ShopUserApp user = new ShopUserApp();

      BeanUtils.copyProperties(userDto, user);
      user.setIsAvailable(true);

      Set<Role> roles = new HashSet<>();

      userDto.getRoles().forEach(item -> {
        Role role = new Role();
        role.setId(item);
        roles.add(role);
      });

      String passwordHashed = encoder.encode(user.getPassword());

      user.setPassword(passwordHashed);

      user.setRoles(roles);

      userCreated = userAppRepository.save(user);

    } catch (Exception e) {
      logger.error("error to create a new user" + e.getMessage());
    }

    return userCreated;
  }

  @Override
  @Transactional(readOnly = true)
  public ShopUserApp findUserByUsername(String username) {
    try {
      Optional<ShopUserApp> user = userAppRepository.findByUsername(username);

      if (user.isPresent()) {
        return user.get();
      }
    } catch (Exception e) {
      logger.error("error to find user by username " + e.getMessage());
    }

    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ShopUserApp> findUserByEmail(String email) {
    return userAppRepository.findByEmail(email);
  }

  public List<String> convertSetRolesToList(Set<Role> roles) {
    return roles.stream().map(item -> item.getName()).collect(Collectors.toList());
  }
}
