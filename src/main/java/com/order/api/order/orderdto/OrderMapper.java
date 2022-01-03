package com.order.api.order.orderdto;

import com.order.api.customer.customerdto.CustomerMapper;
import com.order.domain.order.ItemGroup;
import com.order.domain.order.Order;
import com.order.domain.users.Customer;
import com.order.services.item.ItemService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private static ItemService itemService;

    public OrderMapper(ItemService itemService) {
        OrderMapper.itemService = itemService;
    }

    public static Order mapToOrder(InitializerOrderDto initializerOrderDto, Customer customer) {
        return new Order(customer,getListOfItemGroups(initializerOrderDto.initializerItemGroupDtos()));
    }

    public static OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getUniqueId(), CustomerMapper.mapToCostumerDto(order.getCustomer()), order.getItemGroups());
    }

    private static List<ItemGroup> getListOfItemGroups(List<InitializerItemGroupDto> initializerItemGroupDto) {
        return initializerItemGroupDto.stream()
                .map(initializerItemGroupDto1 -> new ItemGroup(itemService.getItemById(initializerItemGroupDto1.itemID()), initializerItemGroupDto1.amount()))
                .collect(Collectors.toList());
    }
}
