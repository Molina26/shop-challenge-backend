package com.wurthmex.mx.shop.constraints.validations;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wurthmex.mx.shop.constraints.annotations.RolesAccepted;
import com.wurthmex.mx.shop.entities.Role;
import com.wurthmex.mx.shop.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class RolesAcceptedValidator implements ConstraintValidator<RolesAccepted, List<Integer>> {

  @Autowired
  private RoleServiceImpl roleServiceImpl;

  private List<Role> roles;

  @Override
  public void initialize(RolesAccepted constraintAnnotation) {
    this.roles = roleServiceImpl.findRoles();
  }

  @Override
  public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
    boolean flag = false;

    if (value != null) {
      if (!value.isEmpty()) {
        var idsRoles = roles.stream().filter(role -> role.getId() != 0).map(role -> role.getId()).collect(Collectors.toList());

          for (Integer idDto : value) {
            if (idsRoles.contains(idDto)) {
              flag = true;
            } else {
              flag = false;
            }
          }
      }
    }

    return flag;
  }


}
