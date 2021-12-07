package com.order.api.customerdto;

import com.order.domain.Address;

public record CustomerDto(String uniqueId, String firstName, String lastName, String emailAddress, Address address,
                          String phoneNumber) {
}
