package com.order.api;

import com.order.api.customerdto.CustomerDto;
import com.order.api.customerdto.InitializeCostumerDto;
import com.order.domain.Address;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    void name() {
    }
    //    @Test
//    void givenCorrectCustomer_storedInRepository() {
//        Address address = new Address("SesameStreet","210","3000","Leuven");
//        InitializeCostumerDto initializeCostumerDto = new InitializeCostumerDto("Jhon","Fitzgerald Kennedy","jfk@outlook.com",address,"+1487565478");
//        CustomerDto customerDto =
//                RestAssured
//                        .given()
//                        .body(initializeCostumerDto)
//                        .accept(JSON)
//                        .contentType(JSON)
//                        .when()
//                        .port(port)
//                        .post("/costumers")
//                        .then()
//                        .assertThat()
//                        .statusCode(HttpStatus.CREATED.value())
//                        .extract()
//                        .as(CustomerDto.class);
//
//    }
}