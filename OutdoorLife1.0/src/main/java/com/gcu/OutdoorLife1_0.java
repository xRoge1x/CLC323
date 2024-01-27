package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.gcu" })
@SpringBootApplication
public class OutdoorLife1_0 {

	public static void main(String[] args) {
		SpringApplication.run(OutdoorLife1_0.class, args);
	}

}
