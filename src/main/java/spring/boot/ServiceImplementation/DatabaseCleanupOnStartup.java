package spring.boot.ServiceImplementation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.Repository.UserRepository;

@Configuration
public class DatabaseCleanupOnStartup {

    private final UserRepository userRepository;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public DatabaseCleanupOnStartup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    @Transactional
    public CommandLineRunner cleanDatabaseOnStartup() {
        return args -> {
            if ("dev".equals(activeProfile)) {
                System.out.println("Cleaning up the database on startup (Development environment)...");
                userRepository.deleteAll(); // Leeg de gebruikers tabel of voeg andere logica toe
            }
        };
    }
}