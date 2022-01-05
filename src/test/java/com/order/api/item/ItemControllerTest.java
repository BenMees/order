package com.order.api.item;

import com.order.Utility;
import com.order.api.item.itemdto.InitializerItemDto;
import com.order.api.item.itemdto.ItemDto;
import com.order.api.item.itemdto.ItemMapper;
import com.order.domain.item.Item;
import com.order.domain.users.Address;
import com.order.domain.users.Admin;
import com.order.domain.users.Customer;
import com.order.repository.item.ItemRepositoryDb;
import com.order.repository.users.AdminRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.http.ContentType.JSON;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ItemControllerTest {

    @Value("${server.port}")
    private int port;
    private final AdminRepository adminRepository;
    private final ItemRepositoryDb itemRepositoryDb;

    @Autowired
    ItemControllerTest(AdminRepository adminRepository, ItemRepositoryDb itemRepositoryDb) {
        this.adminRepository = adminRepository;
        this.itemRepositoryDb = itemRepositoryDb;
    }

    @Test
    void givenCorrectItemAndAdmin_ItemStored() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Admin admin = new Admin("Admin", "Admin", "admin@outlook.com", address, "04");
        InitializerItemDto initializerItemDto = new InitializerItemDto("BalAdd", "round", 24.5, 4);
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

        Assertions.assertThat(itemDto.name()).isEqualTo(initializerItemDto.name());
        Assertions.assertThat(itemDto.amountInStock()).isEqualTo(initializerItemDto.amountInStock());
        Assertions.assertThat(itemDto.description()).isEqualTo(initializerItemDto.description());
        Assertions.assertThat(itemDto.priceInEuro()).isEqualTo(initializerItemDto.priceInEuro());
    }

    @Test
    void addingTwoTimesItemWithSameName_givesBadRequest() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Admin admin = new Admin("Admin", "Admin", "admin@outlook.com", address, "04");
        InitializerItemDto initializerItemDto = new InitializerItemDto("duplicateBal", "round", 24.5, 4);
        adminRepository.addAdmin(admin);

        Item item = ItemMapper.mapToItem(initializerItemDto);
        itemRepositoryDb.save(item);

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
                .statusCode(HttpStatus.BAD_REQUEST.value());

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

    @Test
    void givenCorrectItemToUpdateAndNotInAdminRepository_UnauthorizedException() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Customer customer = new Customer("Admin", "Admin", "User@outlook.com", address, "04");
        InitializerItemDto initializerItemDto = new InitializerItemDto("BalUpdate", "round", 24.5, 4);
        Item item = ItemMapper.mapToItem(initializerItemDto);
        itemRepositoryDb.save(item);
        InitializerItemDto initializerItemDtoUpdated = new InitializerItemDto("BalUpdate", "square", 50, 3);

        RestAssured
                .given()
                .body(initializerItemDtoUpdated)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(customer.getEmailAddress(), "admin"))
                .when()
                .port(port)
                .put("/items/" + item.getUniqueId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCorrectItemToUpdateAndAdminInAdminRepository_UnauthorizedException() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Admin admin = new Admin("updateItemAdmin", "Admin", "updateItemAdmin@outlook.com", address, "04");
        InitializerItemDto initializerItemDto = new InitializerItemDto("BalUpdate", "round", 24.5, 4);
        Item item = ItemMapper.mapToItem(initializerItemDto);
        itemRepositoryDb.save(item);
        adminRepository.addAdmin(admin);

        InitializerItemDto initializerItemDtoUpdated = new InitializerItemDto("BalUpdate", "square", 50, 3);

        ItemDto itemDto =
                RestAssured
                        .given()
                        .body(initializerItemDtoUpdated)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("Authorization", Utility.generateBase64Authorization(admin.getEmailAddress(), "admin"))
                        .when()
                        .port(port)
                        .put("/items/" + item.getUniqueId())
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .extract()
                        .as(ItemDto.class);

        Assertions.assertThat(itemDto.name()).isEqualTo(initializerItemDtoUpdated.name());
        Assertions.assertThat(itemDto.amountInStock()).isEqualTo(initializerItemDtoUpdated.amountInStock());
        Assertions.assertThat(itemDto.description()).isEqualTo(initializerItemDtoUpdated.description());
        Assertions.assertThat(itemDto.priceInEuro()).isEqualTo(initializerItemDtoUpdated.priceInEuro());
    }
}