package com.sti.bootcamp.WalletProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan("com.sti.bootcamp.WalletProject.model")
@EnableJpaRepositories("com.sti.bootcamp.WalletProject.repository")
public class WalletProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletProjectApplication.class, args);
	}

}
