package spring.boot.Role;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.Entity.UserEntity;

import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}