package com.dev.bruno.findusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(
        exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class}
    )
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}