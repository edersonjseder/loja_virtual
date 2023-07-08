package com.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.lojavirtual.model")
@ComponentScan(basePackages = {"com.lojavirtual.*"})
@EnableJpaRepositories(basePackages = {"com.lojavirtual.repository"})
@EnableTransactionManagement
public class EcommerceLojaVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceLojaVirtualApplication.class, args);
	}

}
