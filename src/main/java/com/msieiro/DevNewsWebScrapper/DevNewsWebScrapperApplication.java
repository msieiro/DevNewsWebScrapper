package com.msieiro.DevNewsWebScrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
class DevNewsWebScrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevNewsWebScrapperApplication.class, args);
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	WebDriver webDriver() {
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
		return new ChromeDriver(options);
	}

}
