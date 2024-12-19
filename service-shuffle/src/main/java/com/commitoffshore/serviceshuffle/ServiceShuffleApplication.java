package com.commitoffshore.serviceshuffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients(basePackages = "com.commitoffshore.serviceshuffle.client")
public class ServiceShuffleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceShuffleApplication.class, args);
    }

}
