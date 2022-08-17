package com.example.springresttemplate.constraints.validations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springresttemplate.constraints.annotations.UniqueEmail;
import com.example.springresttemplate.entities.UserApp;
import com.example.springresttemplate.services.UserAppServiceImpl;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  private UserAppServiceImpl userAppServiceImpl;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean flag = false;
    value = value.toLowerCase();

    Optional<UserApp> user = userAppServiceImpl.findUserByEmail(value);

    if (!user.isPresent()) {
      flag = true;
    }

    return flag;
  }

}
