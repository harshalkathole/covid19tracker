package com.covid19.covid19tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class covid19TrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(covid19TrackerApplication.class, args);
	}

}
