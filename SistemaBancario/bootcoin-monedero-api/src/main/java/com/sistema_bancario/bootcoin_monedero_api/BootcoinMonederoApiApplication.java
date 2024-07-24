package com.sistema_bancario.bootcoin_monedero_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BootcoinMonederoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcoinMonederoApiApplication.class, args);
	}

}
