package com.averagejoedev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by voncount on 7/13/16.
 */
@SpringBootApplication(
        scanBasePackages = "com.averagejoedev"
)
public class AppConfig {

	public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}
