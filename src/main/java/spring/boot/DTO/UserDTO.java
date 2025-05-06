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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
