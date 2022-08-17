package com.wurthmex.mx.shop.constraints.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wurthmex.mx.shop.constraints.validations.TextLengthValidator;

@Constraint(validatedBy = TextLengthValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TextLength {
  String message() default "{springresttemplate.constraints.text.length.message}";

  long min() default 0;

  long max() default 1;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};  
}
