package com.order.services;

import com.order.domain.Customer;
import com.order.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository costumerRepository;

    public CustomerService(CustomerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    public Customer addCostumer(Customer costumer) {
        return costumerRepository.addCostumer(costumer);
    }
}
