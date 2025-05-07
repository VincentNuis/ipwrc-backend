package spring.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.Entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}