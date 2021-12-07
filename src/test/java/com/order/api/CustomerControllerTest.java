package com.order.api;

import com.order.api.customer.customerdto.CustomerDto;
import com.order.api.customer.customerdto.CustomerMapper;
import com.order.api.customer.customerdto.InitializeCustomerDto;
import com.order.domain.users.Address;
import com.order.repository.CustomerRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {

    @Value("${server.port}")
    private int port;
    private final CustomerRepository customerRepository;

    @Autowired
    CustomerControllerTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    void givenCorrectCustomer_storedInRepository() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        InitializeCustomerDto initializeCostumerDto = new InitializeCustomerDto("Jhon", "Fitzgerald Kennedy", "jfk@outlook.com", address, "+1487565478");
        CustomerDto customerDto =
                RestAssured
                        .given()
                        .body(initializeCostumerDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/customers")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(CustomerDto.class);

        Assertions.assertThat(customerDto.address()).isEqualTo(initializeCostumerDto.address());
        Assertions.assertThat(customerDto.emailAddress()).isEqualTo(initializeCostumerDto.emailAddress());
        Assertions.assertThat(customerDto.firstName()).isEqualTo(initializeCostumerDto.firstName());
        Assertions.assertThat(customerDto.lastName()).isEqualTo(initializeCostumerDto.lastName());
        Assertions.assertThat(customerDto.phoneNumber()).isEqualTo(initializeCostumerDto.phoneNumber());
    }

    @Test
    void givenCorrectCustomer_butCustomerWithSameFieldsAlreadyExist_throwBadRequestError() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        InitializeCustomerDto initializeCostumerDto = new InitializeCustomerDto("Jhon2", "Fitzgerald Kennedy", "jfk@outlook.com", address, "+1487565478");
        customerRepository.addCostumer(CustomerMapper.mapToCostumer(initializeCostumerDto));
        RestAssured
                .given()
                .body(initializeCostumerDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

        System.out.println(customerRepository.getCostumers());

    }
}