package com.OutdoorLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.OutdoorLife.LogComponent.ConsoleLoggerInterceptor;
import com.OutdoorLife.LogComponent.LoggerInterceptor;

@ComponentScan({ "com.OutdoorLife" })
@SpringBootApplication
public class Application5{
	public static void main(String[] args) {
		SpringApplication.run(Application5.class, args);
	}

	@Bean
    public LoggerInterceptor loggerInterceptor() {
        return new ConsoleLoggerInterceptor();
    }
	
}