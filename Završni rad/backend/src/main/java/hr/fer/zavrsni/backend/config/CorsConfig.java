package hr.fer.zavrsni.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all paths
            .allowedOrigins("http://localhost:8081") // Set the allowed origin(s)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Set the allowed HTTP methods
            .allowedHeaders("*"); // Set the allowed headers
    }
}

