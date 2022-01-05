package com.order.api.customer;

import com.order.api.customer.customerdto.CustomerDto;
import com.order.api.customer.customerdto.InitializeCustomerDto;
import com.order.api.customer.customerdto.CustomerMapper;
import com.order.domain.users.Customer;
import com.order.domain.users.Feature;
import com.order.services.users.CustomerService;
//import com.order.services.users.CustomerServiceDb;
import com.order.services.users.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger((CustomerController.class));
    private final CustomerService customerServiceDb;
    private final SecurityService securityService;

    public CustomerController(CustomerService customerServiceDb, SecurityService securityService) {
        this.customerServiceDb = customerServiceDb;
        this.securityService = securityService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody InitializeCustomerDto initializeCostumerDto) {
        logger.info("Costumer creation Request");
        Customer costumer = customerServiceDb.addCustomer(CustomerMapper.mapToCostumer(initializeCostumerDto));
        return CustomerMapper.mapToCostumerDto(costumer);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CustomerDto> getCustomers(@RequestHeader String authorization) {
        logger.info("Costumers list requested");
        securityService.validate(authorization, Feature.SEE_CUSTOMERS);
        return customerServiceDb.getCustomers().stream()
                .map(CustomerMapper::mapToCostumerDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDto getCustomer(@PathVariable String id, @RequestHeader String authorization) {
        logger.info("costumer " + id + " requested");
        securityService.validate(authorization, Feature.SEE_CUSTOMERS);
        return CustomerMapper.mapToCostumerDto(customerServiceDb.getCustomerById(id));
    }
}
