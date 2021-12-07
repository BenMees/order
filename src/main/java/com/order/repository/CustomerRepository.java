package com.order.repository;

import com.order.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> costumers = new ConcurrentHashMap<>();

    public Customer addCostumer(Customer costumer) {
        costumers.put(costumer.getUniqueId(), costumer);
        return costumers.get(costumer.getUniqueId());
    }

    public Map<String, Customer> getCostumers() {
        return costumers;
    }
}
