package com.order.domain;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final String uniqueId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        this.uniqueId = UUID.randomUUID().toString();
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.emailAddress = Objects.requireNonNull(emailAddress);
        this.address = Objects.requireNonNull(address);
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uniqueId='" + uniqueId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(uniqueId, customer.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }
}
