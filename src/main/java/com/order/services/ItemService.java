package com.order.services;

import com.order.domain.Item;
import com.order.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepository.addItem(item);
    }

    public Item getItemById(String id) {
        return itemRepository.getItemById(id);
    }

    public Item updateItem(String id,Item updateItem){
        Item item = getItemById(id);
        item.setName(updateItem.getName());
        item.setDescription(updateItem.getDescription());
        item.setAmountInStock(updateItem.getAmountInStock());
        item.setPriceInEuro(updateItem.getPriceInEuro());
        return item;
    }
}
