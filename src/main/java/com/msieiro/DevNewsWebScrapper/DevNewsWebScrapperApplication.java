package com.msieiro.DevNewsWebScrapper;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
class DevNewsWebScrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevNewsWebScrapperApplication.class, args);
	}

	@Bean
	WebDriver webDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				new ClassPathResource("src/main/resources/lib/chromedriver").getPath());
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
		return new ChromeDriver(options);
	}

}
