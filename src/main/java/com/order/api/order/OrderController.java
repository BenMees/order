package com.order.api.order;

import com.order.api.customer.CustomerController;
import com.order.api.order.orderdto.InitializerOrderDto;
import com.order.api.order.orderdto.OrderDto;
import com.order.api.order.orderdto.OrderMapper;
import com.order.domain.order.Order;
import com.order.domain.users.Customer;
import com.order.domain.users.Feature;
import com.order.services.order.OrderService;
import com.order.services.users.UserService;
import com.order.services.users.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;
    private final UserService userService;
    private final Logger logger= LoggerFactory.getLogger((CustomerController.class));


    public OrderController(OrderService orderService, SecurityService securityService, UserService userService) {
        this.orderService = orderService;
        this.securityService = securityService;
        this.userService = userService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody InitializerOrderDto initializerOrderDto, @RequestHeader String authorization){
        logger.info("Order creating requested");
        securityService.validate(authorization, Feature.ORDER);
        Order order = orderService.addOrder(OrderMapper.mapToOrder(initializerOrderDto,(Customer) userService.getUserByEmail(securityService.decodeAuthorization(authorization))));
        return OrderMapper.mapToOrderDto(order);
    }
}
