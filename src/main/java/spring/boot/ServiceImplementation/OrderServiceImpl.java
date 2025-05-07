package spring.boot.ServiceImplementation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import spring.boot.Entity.OrderEntity;
import spring.boot.Repository.OrderRepository;
import spring.boot.Service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }
}