package com.example.overclockAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class OverclockApiApplication {

	public static void main(String[] args) {

		String dbHost = System.getenv("DB_HOST");
		String dbUser = System.getenv("DB_USER");
		String dbPassword = System.getenv("DB_PASSWORD");
		String jwtSecret = System.getenv("JWT_SECRET");

		if (dbHost == null || dbUser == null || dbPassword == null || jwtSecret == null) {
			Dotenv dotenv = Dotenv.load();
			dbHost = dotenv.get("DB_HOST");
			dbUser = dotenv.get("DB_USER");
			dbPassword = dotenv.get("DB_PASSWORD");
			jwtSecret = dotenv.get("JWT_SECRET");
		}

		System.setProperty("spring.datasource.url", Objects.requireNonNull(dbHost));
		System.setProperty("spring.datasource.username", Objects.requireNonNull(dbUser));
		System.setProperty("spring.datasource.password", Objects.requireNonNull(dbPassword));
		System.setProperty("api.security.token.security", Objects.requireNonNull(jwtSecret));

		SpringApplication.run(OverclockApiApplication.class, args);
	}
}