package spring.boot.Service;

import spring.boot.Entity.OrderEntity;

public interface OrderService {
    public OrderEntity saveOrder(OrderEntity order);
}
