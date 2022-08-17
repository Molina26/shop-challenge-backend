package com.wurthmex.mx.shop.constraints.validations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wurthmex.mx.shop.constraints.annotations.UniqueEmail;
import com.wurthmex.mx.shop.entities.ShopUserApp;
import com.wurthmex.mx.shop.services.ShopUserAppServiceImpl;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  private ShopUserAppServiceImpl userAppServiceImpl;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean flag = false;
    value = value.toLowerCase();

    Optional<ShopUserApp> user = userAppServiceImpl.findUserByEmail(value);

    if (!user.isPresent()) {
      flag = true;
    }

    return flag;
  }

}
