package com.samaddico.springboot.service;

import com.samaddico.springboot.model.Item;
import com.samaddico.springboot.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;
    @Mock
    private ItemRepository itemRepository;

    private Item item;

    @Before
    public void setUp() {
        item = new Item();
        item.setName("Polo Shirt");
        item.setPrice(new BigDecimal("15.5"));
        item.setCategory("Fashion");
        item.setQuantity(3);
        item = itemService.addItem(item);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_addItem(){
        when(itemRepository.save(item)).thenReturn(item);
        Item actual = itemService.addItem(item);
        verify(itemRepository, times(1)).save(any(Item.class));
        Assert.assertEquals(item,actual);
    }

    @Test
    public void test_listAll(){
        List<Item> emplist = new ArrayList<>();
        emplist.add(item);

        when(itemRepository.findAll()).thenReturn(emplist);
        List<Item> actual = itemService.getAllItems();
        verify(itemRepository, times(1)).findAll();
        Assert.assertEquals(emplist.size(),actual.size());
    }

    @Test
    public void test_findByName(){
        String name = "Polo Shirt";

        when(itemRepository.findItemByName(name)).thenReturn(item);
        Item actual = itemService.getItemsByName(name);
        verify(itemRepository, times(1)).findItemByName(name);
        Assert.assertEquals(actual,item);
    }

}
