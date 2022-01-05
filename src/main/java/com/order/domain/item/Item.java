package com.order.domain.item;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "uniqueid")
    private String uniqueId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "amountinstock")
    private int amountInStock;
    @Column(name = "priceineuro")
    private double priceInEuro;

    public Item(String name, String description, double priceInEuro, int amountInStock) {
        this.uniqueId = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.priceInEuro = priceInEuro;
        this.amountInStock = amountInStock;
    }

    public Item(Item itemToClone) {
        this.uniqueId = itemToClone.getUniqueId();
        this.description = itemToClone.getDescription();
        this.amountInStock = itemToClone.getAmountInStock();
        this.name = itemToClone.getName();
        this.priceInEuro = itemToClone.getPriceInEuro();
    }

    public Item() {

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
