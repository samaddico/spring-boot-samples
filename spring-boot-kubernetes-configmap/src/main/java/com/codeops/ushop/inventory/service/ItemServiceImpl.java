package com.codeops.ushop.inventory.service;

import com.codeops.ushop.inventory.model.Item;
import com.codeops.ushop.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void removeItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item getItemsByName(String name) {
        return itemRepository.findItemByName(name);
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }
}