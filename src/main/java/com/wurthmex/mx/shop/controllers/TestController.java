package com.wurthmex.mx.shop.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop-challenge/test")
public class TestController {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public TestController(BCryptPasswordEncoder encoder) {
    this.bCryptPasswordEncoder = encoder;
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
  public ResponseEntity<String> test1() {
    return ResponseEntity.ok("This is a protected rute to all roles");
  }

  @GetMapping("/ADMIN")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> test2() {
    return ResponseEntity.ok("This is a protected rute to admin 's roles");
  }

  @GetMapping("/client")
  @PreAuthorize("hasRole('CLIENT')")
  public ResponseEntity<String> test3() {
    return ResponseEntity.ok("This is a protected rute to client's roles");
  }

  @GetMapping("/password")
  public ResponseEntity<?> sendExamplesPasswords() {

    Map<String, String> passwords = new HashMap<>();

    for (int i = 0; i < 3; i++) {
      String key = UUID.randomUUID().toString().substring(0, 5);
      String keyEncoder = bCryptPasswordEncoder.encode(key);
      passwords.put(key, keyEncoder);
    }

    return new ResponseEntity<>(passwords, HttpStatus.OK);
  }

}
