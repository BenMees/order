package com.order.domain;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private final String uniqueId;

    private String name;

    private String description;
    private int amountInStock;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    public void setPriceInEuro(double priceInEuro) {
        this.priceInEuro = priceInEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
