package com.example.springresttemplate.payload.acount.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoUserAuthenticatedResponseDto {
  private String email;
  private String username;
  private List<String> roles;
}
