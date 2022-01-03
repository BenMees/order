package com.order.api.order.orderdto;

import com.order.api.customer.customerdto.CustomerDto;
import com.order.domain.order.ItemGroup;

import java.util.List;

public record OrderDto(String uniqueId, CustomerDto customerDto, List<ItemGroup> itemGroups) {
}
