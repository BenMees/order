package com.order.api.item;

import com.order.api.item.itemdto.InitializerItemDto;
import com.order.api.item.itemdto.ItemDto;
import com.order.api.item.itemdto.ItemMapper;
import com.order.domain.item.Item;
import com.order.domain.users.Feature;
import com.order.services.item.ItemServiceDb;
import com.order.services.users.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemServiceDb itemServiceDb;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger((com.order.api.item.ItemController.class));

    public ItemController(ItemServiceDb itemService, SecurityService securityService) {
        this.itemServiceDb = itemService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody InitializerItemDto initializerItemDto, @RequestHeader String authorization) {
        logger.info("Item creation Request");
        securityService.validate(authorization, Feature.ITEM_CONTROL);
        Item item = itemServiceDb.addItem(ItemMapper.mapToItem(initializerItemDto));
        return ItemMapper.mapToItemDto(item);
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDto updateItem(@PathVariable String id, @RequestBody InitializerItemDto initializerItemDto, @RequestHeader String authorization) {
        logger.info("Item " + id + " update Request");
        securityService.validate(authorization, Feature.ITEM_CONTROL);
        Item item = itemServiceDb.updateItem(id, ItemMapper.mapToItem(initializerItemDto));
        return ItemMapper.mapToItemDto(item);
    }
}

