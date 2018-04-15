package me.aliprax.sbootschemamultitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootApplication
public class SbootSchemaMultitenancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootSchemaMultitenancyApplication.class, args);
	}


}
