package com.example.accounts_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApiApplication.class, args);
	}

}
