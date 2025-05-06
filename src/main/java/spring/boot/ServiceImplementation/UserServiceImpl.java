package spring.boot.ServiceImplementation;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.DTO.UserDTO;
import spring.boot.Entity.UserEntity;
import spring.boot.Repository.UserRepository;
import spring.boot.Role.UserRole;
import spring.boot.Service.UserService;

import java.security.Key;
import java.sql.Date;
import java.util.*;

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
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        System.out.println("Getting All Users!");
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getRoles());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }
}