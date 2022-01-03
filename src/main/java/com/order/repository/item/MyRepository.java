package com.order.repository.item;

import com.order.domain.item.Item;

import java.util.List;

public interface MyRepository {
    Item addItem(Item item);
    Item getItemById(String id);
    List<Item> getItems();
}
