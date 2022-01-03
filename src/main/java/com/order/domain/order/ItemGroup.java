package com.order.domain.order;

import com.order.domain.item.Item;

import java.time.LocalDate;

public class ItemGroup {
    public static final int DAYS_TO_SHIP_IN_STOCK = 1;
    public static final int DAYS_TO_SHIP_NOT_IN_STOCK = 7;
    private final Item item;
    private final int amountOrdered;
    private final LocalDate shippingDate;

    public ItemGroup(Item item, int amount) {
        this.item = new Item(item);
        this.amountOrdered = amount;
        this.shippingDate = calculateShippingDate();
    }

    public double priceInEuro() {
        return amountOrdered * item.getPriceInEuro();
    }

    private LocalDate calculateShippingDate() {
        if (IsInStock()) {
            return LocalDate.now().plusDays(DAYS_TO_SHIP_IN_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_SHIP_NOT_IN_STOCK);
    }

    private boolean IsInStock() {
        return item.getAmountInStock() <= amountOrdered;
    }
}
