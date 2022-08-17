package com.wurthmex.mx.shop.payload.acount.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wurthmex.mx.shop.constraints.annotations.RolesAccepted;
import com.wurthmex.mx.shop.constraints.annotations.TextLength;
import com.wurthmex.mx.shop.constraints.annotations.UniqueEmail;
import com.wurthmex.mx.shop.constraints.annotations.UniqueUsername;

import lombok.Data;

@Data
public class UserSingupRequestDto {

  @NotBlank
  @UniqueUsername
  private String username;

  @NotBlank
  @TextLength(min = 5, max = 15)
  private String password;

  @Email
  @NotBlank
  @UniqueEmail
  private String email;

  @NotNull
  @RolesAccepted
  private List<Integer> roles;

  public void normalizeInfoToUserSignup() {
    this.password = password.trim();
    this.email = this.email.toLowerCase();
  }
}
