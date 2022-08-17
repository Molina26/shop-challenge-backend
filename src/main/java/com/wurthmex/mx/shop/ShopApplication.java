package com.wurthmex.mx.shop;

import com.wurthmex.mx.shop.config.AppCustomContext;
import com.wurthmex.mx.shop.util.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApplication {

	@Bean(name = "AppProperties")
	public static AppProperties getAppProperties() {
		return new AppProperties();
	}

	@Bean
	public static AppCustomContext appCustomContext() {
		return new AppCustomContext();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
