package com.example.springresttemplate.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppCustomContext implements ApplicationContextAware{
  
  private static ApplicationContext CONTEXT;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    CONTEXT = applicationContext;  
  }

  public static Object getBean(String beanName) {
    return CONTEXT.getBean(beanName);
  }

}
