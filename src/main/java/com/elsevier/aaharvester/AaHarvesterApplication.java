package com.elsevier.aaharvester;

import com.elsevier.aaharvester.client.AARestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class AaHarvesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AaHarvesterApplication.class, args);
    }

}
