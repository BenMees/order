package com.order.services.users;

import com.order.domain.users.Customer;

import java.util.List;

public interface CustomerServiceTemplate {
    Customer addCustomer(Customer costumer);
    List<Customer> getCustomersByEmail(String email);
    List<Customer> getCustomers();
    Customer getCustomerById(String id);
}
