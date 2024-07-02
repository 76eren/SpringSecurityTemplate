package org.example.springsecuritytemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSecurityTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityTemplateApplication.class, args);
    }

}
