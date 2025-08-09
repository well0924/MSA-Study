package com.event.msalearningproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.event.*"})
@EnableJpaAuditing
public class MsaLearningProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaLearningProjectApplication.class, args);
    }

}
