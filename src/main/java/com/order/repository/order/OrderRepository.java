package com.order.repository.order;

import com.order.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public List<Order> getOrders() {
        return orders.values().stream().toList();
    }

    public Order addOrder(Order order) {
        orders.put(order.getUniqueId(),order);
        return orders.get(order.getUniqueId());
    }
}
