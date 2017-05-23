package com.websystique.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.websystique.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.websystique.springboot"})
@EnableWebMvc
public class RegistrationCrudApp {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationCrudApp.class, args);
    }
}
