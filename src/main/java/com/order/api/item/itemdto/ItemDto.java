package com.order.api.item.itemdto;

public record ItemDto(String uniqueId, String name, String description, double priceInEuro, int amountInStock) {
}
