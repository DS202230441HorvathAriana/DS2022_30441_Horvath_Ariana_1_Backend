package com.example.energyutility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class EnergyUtilityApplication {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }
    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(EnergyUtilityApplication.class, args);
    }

}
