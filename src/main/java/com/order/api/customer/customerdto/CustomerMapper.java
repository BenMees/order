package com.order.api.customer.customerdto;

import com.order.domain.users.Customer;

public class CustomerMapper {
    public static Customer mapToCostumer(InitializeCustomerDto initializeCostumerDto) {
        return new Customer(initializeCostumerDto.firstName(), initializeCostumerDto.lastName(), initializeCostumerDto.emailAddress(), initializeCostumerDto.address(), initializeCostumerDto.phoneNumber());
    }

    public static CustomerDto mapToCostumerDto(Customer customer) {
        return new CustomerDto(customer.getUniqueId(), customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getAddress(), customer.getPhoneNumber());
    }
}
