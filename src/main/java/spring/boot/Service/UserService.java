package spring.boot.Service;


import org.springframework.http.ResponseEntity;
import spring.boot.Entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity register(UserEntity user);
    ResponseEntity<?> login(String email, String password);
    List<UserEntity> getAllUsers();
}