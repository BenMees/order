package com.order.domain.users;

import java.util.List;

public class Customer extends User{
    private static final List<Feature> FEATURES = List.of(Feature.ORDER);

    public Customer(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        super(firstName, lastName, emailAddress, address, phoneNumber, FEATURES);
    }
}
