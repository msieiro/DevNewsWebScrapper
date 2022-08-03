package com.msieiro.DevNewsWebScrapper.infrastructure.config;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SeleniumConfig {

    /* @Bean
    WebDriver webDriver() throws IOException {
        final File resource = new File("./lib/chromedriver");
        resource.setExecutable(true);
        log.info("RESOURCE IS FILE ? {}", resource.isFile());
        log.info("RESOURCE IS EXECUTABLE ? {}", resource.canExecute());
        log.info("RESOURCE getAbsolutePath ? {}", resource.getAbsolutePath().toString());
        log.info("RESOURCE getCanonicalPath ? {}", resource.getCanonicalPath().toString());
        System.setProperty("webdriver.chrome.driver",
                resource.getCanonicalPath().toString());
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
        return new ChromeDriver(options);
    } */
}
