package com.wurthmex.mx.shop.constraints.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wurthmex.mx.shop.constraints.validations.UniqueUsernameValidator;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
  String message() default "{springresttemplate.constraints.username.unique.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};  
}
