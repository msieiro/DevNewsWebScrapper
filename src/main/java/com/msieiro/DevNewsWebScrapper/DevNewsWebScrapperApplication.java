package com.msieiro.DevNewsWebScrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
class DevNewsWebScrapperApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevNewsWebScrapperApplication.class, args);
    }
}
