package com.example.springresttemplate.payload.acount.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserSinginRequestDto {
  @NotBlank
  private String username;

  @NotBlank
  private String password;
}
