package com.myapp.myusersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.myapp")
@EnableJpaRepositories(basePackages = "com.myapp.myusersservice.repositories")
@EntityScan(basePackages = "com.myapp.myusersservice.entities")
public class MyUsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyUsersServiceApplication.class, args);
    }

}
