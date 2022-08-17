package com.wurthmex.mx.shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AppProperties {

  @Autowired
  private Environment environment;
  
  public String getToKenSecret() {
    return environment.getProperty("token.secret");
  }

}
