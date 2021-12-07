package com.order.repository;

import com.order.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    private final Map<String, Item> items = new ConcurrentHashMap<>();

    public Map<String, Item> getItems() {
        return items;
    }

    public Item addItem(Item item) {
        return items.put(item.getUniqueId(), item);
    }
}
