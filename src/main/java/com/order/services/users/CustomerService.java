package com.order.services.users;

import com.order.domain.users.Customer;
import com.order.exceptions.ObjectAlreadyExistException;
import com.order.repository.users.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository costumerRepository) {
        this.customerRepository = costumerRepository;
    }

    public Customer addCustomer(Customer costumer) {
        if (customerRepository.getCostumers().contains(costumer)) {
            throw new ObjectAlreadyExistException("Requested costumer");
        }
        return customerRepository.addCostumer(costumer);
    }

    public List<Customer> getCustomersByEmail(String email){
        return customerRepository.getCostumers().stream()
                .filter(admin -> admin.getEmailAddress().equals(email))
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomers() {
        return customerRepository.getCostumers();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.getCostumerById(id);
    }
}
