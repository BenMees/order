package com.order.api.item;

import com.order.Utility;
import com.order.api.item.itemdto.InitializerItemDto;
import com.order.api.item.itemdto.ItemDto;
import com.order.domain.users.Address;
import com.order.domain.users.Admin;
import com.order.domain.users.Customer;
import com.order.repository.users.AdminRepository;
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
class ItemControllerTest {

    @Value("${server.port}")
    private int port;
    private final AdminRepository adminRepository;

    @Autowired
    ItemControllerTest(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Test
    void givenCorrectItemAndAdmin_ItemStored() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Admin admin = new Admin("Admin", "Admin", "admin@outlook.com", address, "04");
        InitializerItemDto initializerItemDto = new InitializerItemDto("Bal", "round", 24.5, 4);
        adminRepository.addAdmin(admin);

        ItemDto itemDto =
                RestAssured
                        .given()
                        .body(initializerItemDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("Authorization", Utility.generateBase64Authorization(admin.getEmailAddress(), "admin"))
                        .when()
                        .port(port)
                        .post("/items")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ItemDto.class);
        System.out.println(itemDto);
        Assertions.assertThat(itemDto.name()).isEqualTo(initializerItemDto.name());
    }

    @Test
    void givenCorrectItemAndNotInAdminRepository_UnauthorizedException() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Customer customer = new Customer("Admin", "Admin", "User@outlook.com", address, "04");
        InitializerItemDto initializerItemDto = new InitializerItemDto("Bal", "round", 24.5, 4);

        RestAssured
                .given()
                .body(initializerItemDto)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(customer.getEmailAddress(), "admin"))
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}