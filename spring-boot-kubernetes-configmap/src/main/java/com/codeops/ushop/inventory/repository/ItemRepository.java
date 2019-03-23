package com.codeops.ushop.inventory.repository;

import com.codeops.ushop.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    public Item findItemByName(String name);

}
