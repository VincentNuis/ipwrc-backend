package spring.boot.ServiceImplementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.boot.Entity.UserEntity;
import spring.boot.Repository.ProductRepository;
import spring.boot.Repository.UserRepository;
import spring.boot.Role.UserRole;

import java.util.List;

@Configuration
public class DatabaseSetupOnStartup {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private final EntityManager entityManager;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public DatabaseSetupOnStartup(UserRepository userRepository,
                                  ProductRepository productRepository,
                                  PasswordEncoder passwordEncoder,
                                  EntityManager entityManager) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
    }

    @Bean
    @Transactional
    public CommandLineRunner cleanDatabaseOnStartup() {
        return args -> {
            if ("dev".equals(activeProfile)) {
                System.out.println("Cleaning up the database on startup (Development environment)...");
                userRepository.deleteAll();
                productRepository.deleteAll();
                addDefaultAdmin();
            }
        };
    }

    @Transactional
    private void resetAutoIncrement() {
        entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
    }

    private void addDefaultAdmin() {
        if (userRepository.findByEmail("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setEmail("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            UserRole adminRole = new UserRole();
            adminRole.setRole("ADMIN");
            adminRole.setUser(admin);

            admin.setRoles(List.of(adminRole));

            userRepository.save(admin);
            System.out.println("Default admin added: admin");
        } else {
            System.out.println("Default admin already exists.");
        }
    }
}