package com.example.springresttemplate.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springresttemplate.entities.Role;
import com.example.springresttemplate.entities.UserApp;
import com.example.springresttemplate.payload.acount.request.UserSingupRequestDto;
import com.example.springresttemplate.repositories.UserAppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService {

  private final Logger logger = LoggerFactory.getLogger(UserAppServiceImpl.class);

  private final UserAppRepository userAppRepository;

  private final BCryptPasswordEncoder encoder;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {

    Optional<UserApp> user = userAppRepository.findByUsername(username);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }

    UserApp userToAuthenticate = user.get();

    return new User(userToAuthenticate.getUsername(), userToAuthenticate.getPassword(), new ArrayList<>());
  }

  @Override
  public UserApp createUser(UserSingupRequestDto userDto) {

    UserApp userCreated = null;

    try {
      UserApp user = new UserApp();

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
  public UserApp findUserByUsername(String username) {
    try {
      Optional<UserApp> user = userAppRepository.findByUsername(username);

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
  public Optional<UserApp> findUserByEmail(String email) {
    return userAppRepository.findByEmail(email);
  }

  public List<String> convertSetRolesToList(Set<Role> roles) {
    return roles.stream().map(item -> item.getName()).collect(Collectors.toList());
  }
}
