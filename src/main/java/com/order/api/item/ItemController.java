package com.order.api.item;

import com.order.api.item.itemdto.InitializerItemDto;
import com.order.api.item.itemdto.ItemDto;
import com.order.api.item.itemdto.ItemMapper;
import com.order.domain.Item;
import com.order.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger((com.order.api.item.ItemController.class));

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody InitializerItemDto initializerItemDto) {
        logger.info("Item creation Request");
        Item item = itemService.addItem(ItemMapper.mapToItem(initializerItemDto));
        return ItemMapper.mapToItemDto(item);
    }
}

