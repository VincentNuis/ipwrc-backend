package spring.boot.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.model.UserModel;
import spring.boot.services.UserService;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService service;

    public UserController(@Qualifier("primary") UserService userService){
        this.service = userService;
    }

    @GetMapping("/getUser/{userName}")
    public ResponseEntity<UserModel> getUser(@PathVariable String userName){
        return ResponseEntity.of(Optional.of(service.getUser(userName)));
    }

    @PostMapping("/addUser")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserModel user){
        service.addUser(user);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userName){
        service.deleteUser(userName);
        return ResponseEntity.noContent().build();
    }
}
