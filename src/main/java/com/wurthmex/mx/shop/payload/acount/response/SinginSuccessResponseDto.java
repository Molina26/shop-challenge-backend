package com.wurthmex.mx.shop.payload.acount.response;

import java.util.List;

import lombok.Data;

@Data
public class SinginSuccessResponseDto {
  private String token;
  private String username;
  private String email;
  private List<String> roles;

  public SinginSuccessResponseDto() {

  }

  public SinginSuccessResponseDto(String accessToken, String username, String email, List<String> roles) {
    this.token = accessToken;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }
}
