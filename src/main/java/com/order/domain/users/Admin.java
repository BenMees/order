package com.order.domain.users;

public class Admin extends User {
    public Admin(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        super(firstName, lastName, emailAddress, address, phoneNumber);
    }
}
