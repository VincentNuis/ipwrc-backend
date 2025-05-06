package spring.boot.ServiceImplementation;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.Entity.UserEntity;
import spring.boot.Repository.UserRepository;
import spring.boot.Role.UserRole;
import spring.boot.Service.UserService;

import java.security.Key;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Key key = Jwts.SIG.HS512.key().build();

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            UserRole defaultRole = new UserRole();
            defaultRole.setRole("USER");
            defaultRole.setUser(user);
            user.setRoles(List.of(defaultRole));
        } else {
            // Koppel bestaande roles aan deze user
            for (UserRole role : user.getRoles()) {
                role.setUser(user);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> login(String email, String rawPassword) {
        System.out.println(email + " " + rawPassword);
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty() || !passwordEncoder.matches(rawPassword, userOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = Jwts.builder()
                .setSubject(email)
                .claim("roles", userOpt.get().getRoles())
//                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();

        return ResponseEntity.ok(token);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}