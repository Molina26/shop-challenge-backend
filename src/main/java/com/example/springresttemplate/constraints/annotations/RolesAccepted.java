package com.example.springresttemplate.constraints.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.springresttemplate.constraints.validations.RolesAcceptedValidator;

@Constraint(validatedBy = RolesAcceptedValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesAccepted {
  String message() default "{springresttemplate.constraints.roles.accepted.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};  
}
