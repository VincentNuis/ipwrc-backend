package spring.boot.DTO;

import spring.boot.Role.UserRole;

import java.util.List;

public class UserDTO {
    private Long id;
    private String email;
    private List<UserRole> roles;

    // constructors, getters en setters

    public UserDTO(Long id, String email, List<UserRole> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    // getters en setters
}
