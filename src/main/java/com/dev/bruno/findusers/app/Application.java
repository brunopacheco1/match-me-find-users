package com.dev.bruno.findusers.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.dev.bruno.findusers"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}