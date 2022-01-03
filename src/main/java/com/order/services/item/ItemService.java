package com.order.services.item;

import com.order.domain.item.Item;
import com.order.exceptions.ObjectAlreadyExistException;
import com.order.repository.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        if (getItems().contains(item)) {
            throw new ObjectAlreadyExistException("Item with name " + item.getName());
        }
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

    public List<Item> getItems() {
        return itemRepository.getItems();
    }
}
