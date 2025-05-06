package spring.boot.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}