package com.order.services.users;

import com.order.domain.users.Customer;
import com.order.exceptions.ObjectAlreadyExistException;
import com.order.exceptions.ObjectDoesNotExist;
import com.order.repository.item.CustomerRepositoryDb;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceDb implements CustomerServiceTemplate{

    private final CustomerRepositoryDb customerRepository;

    public CustomerServiceDb(CustomerRepositoryDb customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer costumer) {
       if(getCustomers().contains(costumer)) {
           throw new ObjectAlreadyExistException("Customer " + costumer);
       }
        return customerRepository.save(costumer);
    }

    @Override
    public List<Customer> getCustomersByEmail(String email) {
         return customerRepository.findAll().stream()
                 .filter(n -> n.getEmailAddress().equals(email))
                 .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).stream()
                .findFirst()
                .orElseThrow(() -> new ObjectDoesNotExist("Customer with id: " + id + ","));
    }
}
