package com.order.domain.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

class AddressTest {

    @ParameterizedTest (name = "{index} One input of address is null: {arguments} ")
    @MethodSource("provideOneNullInputValueForAddress")
    void givenNullValueOneParameterOfConstructor(String streetName, String houseNumber, String postalCode, String city)  {
        try {
            new Address(streetName, houseNumber, postalCode, city);
            fail();
        }catch (NullPointerException exception){

        }catch (Exception exception){
            fail();
        }
    }

    @Test
    void givenTwoExactAddresses_TheyAreThoughtOfToBeTheSame() {
        Address testAddress = new Address("SesameStreet","5", "3000", "Leuven");
        Address testAddressDuplicate = new Address("SesameStreet","5", "3000", "Leuven");
        Assertions.assertThat(testAddress.equals(testAddressDuplicate)).isTrue();
    }

    private static Stream<Arguments> provideOneNullInputValueForAddress() {
        return Stream.of(
                Arguments.of(null,"5", "3000", "Leuven"),
                Arguments.of("SesameStreet",null, "3000", "Leuven"),
                Arguments.of("SesameStreet","5", null, "Leuven"),
                Arguments.of("SesameStreet","5", "3000", null)
        );
    }
}