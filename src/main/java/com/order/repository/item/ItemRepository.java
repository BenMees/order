package com.order.repository.item;

import com.order.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    private final Map<String, Item> items = new ConcurrentHashMap<>();

    public Item addItem(Item item) {
        items.put(item.getUniqueId(), item);
        return items.get(item.getUniqueId());
    }

    public Item getItemById(String id) {
        return items.get(id);
    }

    public List<Item> getItems() {
        return items.values().stream().toList();
    }
}
