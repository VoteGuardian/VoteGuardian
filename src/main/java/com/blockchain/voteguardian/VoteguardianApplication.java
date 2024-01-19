package com.blockchain.voteguardian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class VoteguardianApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteguardianApplication.class, args);
	}

}
