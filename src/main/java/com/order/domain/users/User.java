package com.order.domain.users;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class User {
    private final String uniqueId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final List<Feature> features;

    public User(String firstName, String lastName, String emailAddress, Address address, String phoneNumber, List<Feature> features) {
        this.features = features;
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

    public boolean isAble(Feature feature) {
        return features.contains(feature);
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
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(address, user.address) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, emailAddress, address, phoneNumber);
    }
}

