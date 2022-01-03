package com.order.api.item.itemdto;

import com.order.domain.item.Item;

public class ItemMapper {
    public static Item mapToItem(InitializerItemDto initializerItemDto) {
        return new Item(initializerItemDto.name(), initializerItemDto.description(), initializerItemDto.priceInEuro(), initializerItemDto.amountInStock());
    }

    public static ItemDto mapToItemDto(Item item) {
        return new ItemDto(item.getUniqueId(), item.getName(), item.getDescription(), item.getPriceInEuro(), item.getAmountInStock());
    }
}
