package com.order.api.order.orderdto;
import java.util.List;

public record InitializerOrderDto(List<InitializerItemGroupDto> initializerItemGroupDtos) {
}
