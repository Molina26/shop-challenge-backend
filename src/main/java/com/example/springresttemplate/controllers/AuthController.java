package com.example.springresttemplate.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springresttemplate.entities.UserApp;
import com.example.springresttemplate.exceptions.AppCustomException;
import com.example.springresttemplate.payload.acount.request.UserSinginRequestDto;
import com.example.springresttemplate.payload.acount.request.UserSingupRequestDto;
import com.example.springresttemplate.payload.acount.response.InfoUserAuthenticatedResponseDto;
import com.example.springresttemplate.payload.acount.response.SinginSuccessResponseDto;
import com.example.springresttemplate.security.jwt.JwtUtils;
import com.example.springresttemplate.services.UserAppServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/example/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private UserAppServiceImpl userAppServiceImpl;

  @Autowired
  JwtUtils jwtUtils;

  private final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserSinginRequestDto loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserApp userDetails = userAppServiceImpl.findUserByUsername(authentication.getName());

    if (!userDetails.isIsAvailable()) {
      logger.error("user isn't available");
      throw new AppCustomException("authentication", "El usuario esta inhabilitado");
    }

    if (userDetails.getRoles().isEmpty()) {
      logger.error("user haven´t roles");
      throw new AppCustomException("authentication", "El usuario no tiene un rol asignado");
    }

    List<String> roles = userAppServiceImpl.convertSetRolesToList(userDetails.getRoles());

    return ResponseEntity.ok(new SinginSuccessResponseDto(jwt,
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserSingupRequestDto signUpRequest) {

    signUpRequest.normalizeInfoToUserSignup();

    UserApp user = userAppServiceImpl.createUser(signUpRequest);

    if (user != null) {
      return ResponseEntity.ok("User registered successfully!");
    }

    return new ResponseEntity<>("Error to create a user", HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/info-user")
  public ResponseEntity<?> sendInfoUserAuthenticated() {
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    UserApp userInfo = userAppServiceImpl.findUserByUsername(username);

    List<String> roles = userAppServiceImpl.convertSetRolesToList(userInfo.getRoles());

    return ResponseEntity.ok(new InfoUserAuthenticatedResponseDto(userInfo.getEmail(),
        userInfo.getUsername(),
        roles));
  }
}
