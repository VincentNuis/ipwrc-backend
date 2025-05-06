package spring.boot.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable() // CSRF uitschakelen voor API's die geen sessie gebruiken
                .authorizeRequests()
                .requestMatchers("/api/users/login", "/api/users/register", "/public/**").permitAll() // Toegestaan zonder authenticatie
                .anyRequest().authenticated() // Alle andere verzoeken moeten geauthenticeerd zijn
                .and()
                .formLogin().disable() // Uitschakelen van form-login
                .httpBasic().disable(); // Uitschakelen van basic auth, indien nodig voor je API

        return http.build();
    }

    // CORS configureren
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")  // Sta verzoeken van localhost:4200 toe
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);  // Optioneel als je cookies/sessies wilt toestaan
            }
        };
    }
}