package com.example.overclockAPI.infra.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

   @Bean
    public CorsFilter corsFilter(){
       org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration();

      config.setAllowedOriginPatterns(Collections.singletonList("*"));

      // Permite credenciais
      config.setAllowCredentials(true);

      // Permite todos os headers
      config.setAllowedHeaders(Arrays.asList("*"));

      // Headers expostos
      config.setExposedHeaders(Arrays.asList("*"));

      // Permite todos os métodos
      config.setAllowedMethods(Arrays.asList("*"));

      // Tempo de cache das permissões (1 hora)
      config.setMaxAge(3600L);

      source.registerCorsConfiguration("/**", config);
       return new CorsFilter(source);
   }
}
