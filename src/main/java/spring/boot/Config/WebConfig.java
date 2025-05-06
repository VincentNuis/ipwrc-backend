package spring.boot.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("CORS WEBCONFIG STARTED");
        // Toegestane origin voor CORS-verzoeken
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Toegestane origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Toegestane methoden
                .allowedHeaders("*")  // Toegestane headers
                .allowCredentials(true);  // Optioneel, als je ook cookies of sessies wilt toestaan
    }
}