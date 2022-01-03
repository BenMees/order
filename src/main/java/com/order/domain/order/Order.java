package com.order.domain.order;

import com.order.domain.users.Customer;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String uniqueId;
    private final Customer customer;
    private final List<ItemGroup> itemGroups;

    public Order(Customer customer, List<ItemGroup> itemGroups) {
        this.customer = customer;
        this.itemGroups = itemGroups;
        this.uniqueId = UUID.randomUUID().toString();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public double priceInEuro() {
        return itemGroups.stream()
                .map(ItemGroup::priceInEuro)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
