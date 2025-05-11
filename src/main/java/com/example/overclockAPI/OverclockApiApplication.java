package com.example.overclockAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class OverclockApiApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		System.setProperty("spring.datasource.url",
				Objects.requireNonNull(dotenv.get("DB_HOST")));

		System.setProperty("spring.datasource.username",
				Objects.requireNonNull(dotenv.get("DB_USER")));

		System.setProperty("spring.datasource.password",
				Objects.requireNonNull(dotenv.get("DB_PASSWORD")));

		System.setProperty("api.security.token.security",
				Objects.requireNonNull(dotenv.get("JWT_SECRET")));

		SpringApplication.run(OverclockApiApplication.class, args);
	}
}
