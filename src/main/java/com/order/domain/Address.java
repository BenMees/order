package com.order.domain;

import java.util.Objects;

public record Address(String streetName, String houseNumber, String postalCode,
                      String city) {
    public Address(String streetName, String houseNumber, String postalCode, String city) {
        this.streetName = Objects.requireNonNull(streetName);
        this.houseNumber = Objects.requireNonNull(houseNumber);
        this.postalCode = Objects.requireNonNull(postalCode);
        this.city = Objects.requireNonNull(city);
    }
}