package com.order.api.order;

import com.order.Utility;
import com.order.api.customer.customerdto.InitializeCustomerDto;
import com.order.api.order.orderdto.InitializerItemGroupDto;
import com.order.api.order.orderdto.InitializerOrderDto;
import com.order.api.order.orderdto.OrderDto;
import com.order.domain.item.Item;
import com.order.domain.order.ItemGroup;
import com.order.domain.order.Order;
import com.order.domain.users.Address;
import com.order.domain.users.Customer;
import com.order.repository.item.ItemRepository;
import com.order.repository.order.OrderRepository;
import com.order.repository.users.CustomerRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.http.ContentType.JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest {

    @Value("${server.port}")
    private int port;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    OrderControllerTest(CustomerRepository adminRepository, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.customerRepository = adminRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

//    @Test
//    void whenOrderProvedAndCostumerExist_saveOrder() {
//        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
//        Customer customer = new Customer("OrderCustomer", "d", "OrderCustomer@outlook.com", address, "04");
//        customerRepository.addCostumer(customer);
//        Item item1 = new Item("ItemForOrder","Description",12.5,8);
//        Item item2 = new Item("ItemForOrder2","Description",12.5,8);
//        itemRepository.addItem(item1);
//        itemRepository.addItem(item2);
//
//        List<InitializerItemGroupDto> initializerItemGroupDtos = new ArrayList<>();
//        initializerItemGroupDtos.add(new InitializerItemGroupDto(item1.getUniqueId(),2));
//        initializerItemGroupDtos.add(new InitializerItemGroupDto(item2.getUniqueId(),3));
//
//        OrderDto orderDto =
//                RestAssured
//                        .given()
//                        .body(new InitializerOrderDto(initializerItemGroupDtos))
//                        .accept(JSON)
//                        .contentType(JSON)
//                        .header("Authorization", Utility.generateBase64Authorization(customer.getEmailAddress(), "customer"))
//                        .when()
//                        .port(port)
//                        .post("/orders")
//                        .then()
//                        .assertThat()
//                        .statusCode(HttpStatus.CREATED.value())
//                        .extract()
//                        .as(OrderDto.class);
//
//    }
}