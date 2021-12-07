package com.order.domain.users;

public class Customer extends User{
    public Customer(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        super(firstName, lastName, emailAddress, address, phoneNumber);
    }
}
