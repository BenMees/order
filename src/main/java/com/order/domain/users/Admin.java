package com.order.domain.users;

import java.util.List;

public class Admin extends User {
    private static final List<Feature> FEATURES = List.of(Feature.ITEM_CONTROL, Feature.SEE_CUSTOMERS);

    public Admin(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        super(firstName, lastName, emailAddress, address, phoneNumber, FEATURES);
    }
}
