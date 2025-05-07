package spring.boot.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.DTO.UserDTO;
import spring.boot.Entity.UserEntity;
import spring.boot.Service.UserService;
import spring.boot.login.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://orange-water-04e1e9403.6.azurestaticapps.net")
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
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        boolean isRemoved = userService.deleteUser(id);
        if (!isRemoved) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}