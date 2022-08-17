package com.example.springresttemplate.exceptions;

public class AppCustomException extends RuntimeException {

  private String field;

  public AppCustomException() {
  }
  
  public AppCustomException(String field, String message) {
    super(message);
    this.field = field;
  }

  public String getField() {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  
}
