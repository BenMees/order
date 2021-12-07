package com.order.services;

import com.order.domain.users.Customer;
import com.order.exceptions.ObjectAlreadyExistException;
import com.order.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository costumerRepository) {
        this.customerRepository = costumerRepository;
    }

    public Customer addCostumer(Customer costumer) {
        if (customerRepository.getCostumers().contains(costumer)) {
            throw new ObjectAlreadyExistException("Requested costumer");
        }
        return customerRepository.addCostumer(costumer);
    }
}
