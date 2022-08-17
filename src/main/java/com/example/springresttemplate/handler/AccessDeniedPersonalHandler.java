package com.example.springresttemplate.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedPersonalHandler implements AccessDeniedHandler{

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
      writeCustomResponse(response);
  }

  private void writeCustomResponse(HttpServletResponse response) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();  
    String message = "";
    if (!response.isCommitted()) {
          try {
              if (username != null) {
                message = "\"User "+ username + " is not authorized.\"";
              } else {
                message = "\"User is not authorized.\"";
              }
              response.setStatus(HttpStatus.FORBIDDEN.value());
              response.getWriter().write("{ \"error\": "+ message +" }");
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }
  }
}
