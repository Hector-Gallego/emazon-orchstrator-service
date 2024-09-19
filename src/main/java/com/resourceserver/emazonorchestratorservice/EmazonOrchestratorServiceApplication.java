package com.resourceserver.emazonorchestratorservice;

import com.resourceserver.emazonorchestratorservice.configuration.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeyProperties.class)

public class EmazonOrchestratorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonOrchestratorServiceApplication.class, args);
	}

}
