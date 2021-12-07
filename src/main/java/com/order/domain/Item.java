package com.order.domain;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private final String uniqueId;
    private final String name;
    private final String description;
    private final int amountInStock;
    private double priceInEuro;

    public Item(String name, String description, double priceInEuro, int amountInStock) {
        this.uniqueId = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.priceInEuro = priceInEuro;
        this.amountInStock = amountInStock;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public double getPriceInEuro() {
        return priceInEuro;
    }
}
