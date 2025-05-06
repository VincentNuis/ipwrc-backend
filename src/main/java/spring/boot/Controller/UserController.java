package spring.boot.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.DTO.UserDTO;
import spring.boot.Entity.UserEntity;
import spring.boot.Service.UserService;
import spring.boot.login.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        return userService.login(login.getEmail(), login.getPassword());
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        System.out.println("Sending: " + userService.getAllUsers());
        return userService.getAllUsers();
    }
}