package com.order.api.customer.customerdto;

import com.order.domain.users.Address;

public record InitializeCustomerDto(String firstName, String lastName, String emailAddress, Address address,
                                    String phoneNumber) {
}
