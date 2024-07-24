package com.yanki_api.yanki_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YankiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YankiApiApplication.class, args);
	}

}
