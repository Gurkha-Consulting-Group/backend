package com.gurkhaconsultinggroup.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BackendApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public ApplicationRunner logActiveProfiles(Environment environment) {
		return args -> {
			String[] profiles = environment.getActiveProfiles();
			if (profiles.length == 0) {
				LOGGER.info("Active profile: <default>");
			} else {
				LOGGER.info("Active profile(s): {}", String.join(", ", profiles));
			}
		};
	}

}
