package com.order.domain.users;

import java.util.Objects;

public record Address(String streetName, String houseNumber, String postalCode,
                      String city) {
    public Address(String streetName, String houseNumber, String postalCode, String city) {
        this.streetName = Objects.requireNonNull(streetName);
        this.houseNumber = Objects.requireNonNull(houseNumber);
        this.postalCode = Objects.requireNonNull(postalCode);
        this.city = Objects.requireNonNull(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetName, address.streetName) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(postalCode, address.postalCode) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, houseNumber, postalCode, city);
    }
}