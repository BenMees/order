package com.order.api.customer;

import com.order.Utility;
import com.order.api.customer.customerdto.CustomerDto;
import com.order.api.customer.customerdto.CustomerMapper;
import com.order.api.customer.customerdto.InitializeCustomerDto;
import com.order.domain.users.Address;
import com.order.domain.users.Admin;
import com.order.domain.users.Customer;
import com.order.repository.users.AdminRepository;
import com.order.repository.users.CustomerRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static io.restassured.http.ContentType.JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {

    @Value("${server.port}")
    private int port;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    CustomerControllerTest(CustomerRepository customerRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
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
    }

    @Test
    void givenCorrectAdminAsksForViewingAllCustomers_giveListOfCustomers() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Admin admin = new Admin("AdminLookAtAllCustomers", "Admin", "AdminLookAtAllCustomers@outlook.com", address, "04");
        adminRepository.addAdmin(admin);
        InitializeCustomerDto initializeCostumerDto = new InitializeCustomerDto("Jhon3", "Fitzgerald Kennedy", "jfk@outlook.com", address, "+1487565478");
        Customer customer = CustomerMapper.mapToCostumer(initializeCostumerDto);
        customerRepository.addCostumer(customer);

        CustomerDto[] customersDto = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(admin.getEmailAddress(), "admin"))
                .when()
                .port(port)
                .get("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(CustomerDto[].class);

        Assertions.assertThat(customersDto.length).isEqualTo(customerRepository.getCostumers().size());
        Assertions.assertThat(customersDto).contains(CustomerMapper.mapToCostumerDto(customer));
    }

    @Test
    void givenNoAdminAsksForViewingAllCustomers_throwException() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        InitializeCustomerDto initializeCostumerDto = new InitializeCustomerDto("Jhon3", "Fitzgerald Kennedy", "jfk3@outlook.com", address, "+1487565478");
        customerRepository.addCostumer(CustomerMapper.mapToCostumer(initializeCostumerDto));
        RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(initializeCostumerDto.emailAddress(), "admin"))
                .when()
                .port(port)
                .get("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}