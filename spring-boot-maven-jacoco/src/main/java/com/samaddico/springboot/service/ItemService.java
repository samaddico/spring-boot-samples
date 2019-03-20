package com.samaddico.springboot.service;

import com.samaddico.springboot.model.Item;

import java.util.List;

public interface ItemService {

    public Item addItem(Item item);
    public List<Item> getAllItems();
    public void removeItem(Long id);
    public Item getItemsByName(String name);
    public void deleteAll();

}
