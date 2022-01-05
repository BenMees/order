package com.order.services.item;

import com.order.domain.item.Item;
import com.order.exceptions.ObjectAlreadyExistException;
import com.order.repository.item.ItemRepositoryDb;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceDb {
    private final ItemRepositoryDb itemRExpositoryDb;

    public ItemServiceDb(ItemRepositoryDb itemRExpositoryDb) {
        this.itemRExpositoryDb = itemRExpositoryDb;
    }

    public Item addItem(Item item) {
        if (getItems().contains(item)) {
            throw new ObjectAlreadyExistException("Item with name " + item.getName());
        }
        return itemRExpositoryDb.save(item);
    }

    public Item getItemById(String id) {
        return itemRExpositoryDb.getById(id);
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
        return itemRExpositoryDb.findAll();
    }
}
