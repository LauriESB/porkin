package com.porkin.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  public void addCorsMapping(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("https://porkin-lime.vercel.app")
        .allowedMethods("GET", "POST","PUT", "DELETE")
        .allowedHeaders("*");
  }

}
