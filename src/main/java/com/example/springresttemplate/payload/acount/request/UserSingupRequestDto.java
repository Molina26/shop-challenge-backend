package com.example.springresttemplate.payload.acount.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.springresttemplate.constraints.annotations.RolesAccepted;
import com.example.springresttemplate.constraints.annotations.TextLength;
import com.example.springresttemplate.constraints.annotations.UniqueEmail;
import com.example.springresttemplate.constraints.annotations.UniqueUsername;

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
