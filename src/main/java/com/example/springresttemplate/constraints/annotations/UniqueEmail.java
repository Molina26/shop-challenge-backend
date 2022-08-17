package com.example.springresttemplate.constraints.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.springresttemplate.constraints.validations.UniqueEmailValidator;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
  String message() default "{springresttemplate.constraints.email.unique.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};  
}
