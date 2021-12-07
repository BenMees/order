package com.order.api.customer;

import com.order.api.customer.customerdto.CustomerDto;
import com.order.api.customer.customerdto.InitializeCostumerDto;
import com.order.api.customer.customerdto.CustomerMapper;
import com.order.domain.Customer;
import com.order.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService costumerService;
    private final Logger logger = LoggerFactory.getLogger((CustomerController.class));

    public CustomerController(CustomerService costumerService) {
        this.costumerService = costumerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createConsumerDto(@RequestBody InitializeCostumerDto initializeCostumerDto) {
        logger.info("Costumer creation Request");
        Customer costumer = costumerService.addCostumer(CustomerMapper.mapToCostumer(initializeCostumerDto));
        return CustomerMapper.mapToCostumerDto(costumer);
    }
}
