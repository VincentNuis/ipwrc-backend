package spring.boot.ServiceImplementation;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.DTO.UserDTO;
import spring.boot.Entity.UserEntity;
import spring.boot.Repository.UserRepository;
import spring.boot.Role.UserRole;
import spring.boot.Service.UserService;
import spring.boot.jwt.JwtTokenProvider;

import java.security.Key;
import java.sql.Date;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final Key key = Jwts.SIG.HS512.key().build();

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
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

        String token = jwtTokenProvider.generateToken(email, userOpt.get().getRoles());

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