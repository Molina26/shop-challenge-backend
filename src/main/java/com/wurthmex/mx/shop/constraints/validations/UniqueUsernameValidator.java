package com.wurthmex.mx.shop.constraints.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wurthmex.mx.shop.constraints.annotations.UniqueUsername;
import com.wurthmex.mx.shop.entities.ShopUserApp;
import com.wurthmex.mx.shop.services.ShopUserAppServiceImpl;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  @Autowired
  private ShopUserAppServiceImpl userAppServiceImpl;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean flag = false;
    ShopUserApp user = userAppServiceImpl.findUserByUsername(value);
    
    if ( user == null ) { 
      flag = true;
    }

    return flag;
  }

}
