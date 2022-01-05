package com.order.repository.item;

import com.order.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryDb extends JpaRepository<Item, String> {

}
