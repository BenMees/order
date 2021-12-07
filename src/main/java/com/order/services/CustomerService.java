package com.order.services;

//import com.order.api.exceptions.MultipleExistException;
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
//        if (costumerRepository.getCostumers().containsKey(costumer.getUniqueId())) {
////            throw new MultipleExistException(costumer.toString());
//        }
        return costumerRepository.addCostumer(costumer);
    }
}
