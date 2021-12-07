package com.order.api.customer.customerdto;

import com.order.domain.Address;

public record InitializeCostumerDto(String firstName, String lastName, String emailAddress, Address address,
                                    String phoneNumber) {
}
