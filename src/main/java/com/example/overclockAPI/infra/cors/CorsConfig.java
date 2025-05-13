package com.example.overclockAPI.infra.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

   @Bean
    public CorsFilter corsFilter(){
       org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration();

       config.addAllowedOriginPattern("*");

       config.setAllowCredentials(true);

       config.addAllowedHeader("*");

       config.addExposedHeader("Authorization");

       config.addAllowedMethod("*");

       config.setMaxAge(3600L);

       source.registerCorsConfiguration("/**", config);
       return new CorsFilter(source);
   }
}
