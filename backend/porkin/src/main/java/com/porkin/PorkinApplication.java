package com.porkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PorkinApplication {

  public static void main(String[] args) {
    SpringApplication.run(PorkinApplication.class, args);


  }

}
