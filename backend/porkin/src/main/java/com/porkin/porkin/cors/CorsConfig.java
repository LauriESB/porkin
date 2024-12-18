package com.porkin.porkin.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("https://porkin-lime.vercel.app", "http://localhost:8080")
        .allowedMethods("GET", "POST","PUT", "DELETE")
        .allowedHeaders("*");
  }

}
