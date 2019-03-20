package com.fedex.sad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SadEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SadEurekaApplication.class, args);
	}

}
