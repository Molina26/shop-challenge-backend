package com.example.springresttemplate.constraints.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springresttemplate.constraints.annotations.UniqueUsername;
import com.example.springresttemplate.entities.UserApp;
import com.example.springresttemplate.services.UserAppServiceImpl;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  @Autowired
  private UserAppServiceImpl userAppServiceImpl;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean flag = false;
    UserApp user = userAppServiceImpl.findUserByUsername(value);
    
    if ( user == null ) { 
      flag = true;
    }

    return flag;
  }

}
