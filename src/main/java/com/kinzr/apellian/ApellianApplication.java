package com.kinzr.apellian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class ApellianApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApellianApplication.class, args);
	}

}
