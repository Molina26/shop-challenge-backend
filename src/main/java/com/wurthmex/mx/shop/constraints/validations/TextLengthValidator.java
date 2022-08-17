package com.wurthmex.mx.shop.constraints.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wurthmex.mx.shop.constraints.annotations.TextLength;

public class TextLengthValidator implements ConstraintValidator<TextLength, String> {

  private TextLength validator;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean flag = false;

    if (value != null) {
      value = value.trim();
      long sizeText = value.length();

      if (sizeText >= this.validator.min() && sizeText <= this.validator.max()) {
        flag = true;
      }

    }

    return flag;
  }

  @Override
  public void initialize(TextLength constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.validator = constraintAnnotation;
  }

}
