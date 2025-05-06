package spring.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.Entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}