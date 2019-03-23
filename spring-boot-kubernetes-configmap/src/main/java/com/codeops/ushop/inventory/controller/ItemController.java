package com.codeops.ushop.inventory.controller;

import com.codeops.ushop.inventory.form.ItemForm;
import com.codeops.ushop.inventory.form.JsonResponse;
import com.codeops.ushop.inventory.model.Item;
import com.codeops.ushop.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/items/api")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping( value = "/add")
    public JsonResponse create(@RequestBody ItemForm form){

        Item item = new Item();
        item.setCategory(form.getCategory());
        item.setName(form.getName());
        item.setQuantity(form.getQuantity());
        item.setPrice(form.getPrice());
        item.setDateCreated(new Date());

        JsonResponse response = new JsonResponse();
        response.setSuccess(true);
        response.setData(item);
        response.setMessage("Item Created");
        return response;
    }

    @GetMapping(value = "/remove/{id}")
    public JsonResponse delete(@PathVariable("id") Long id){
        itemService.removeItem(id);
        JsonResponse response = new JsonResponse();
        response.setSuccess(true);
        response.setMessage("Item Deleted");
        return response;
    }

    @GetMapping(value = "/find")
    public JsonResponse findByName(@RequestParam("name") String name){
        Item item = itemService.getItemsByName(name);
        JsonResponse response = new JsonResponse();
        response.setSuccess(true);
        response.setData(item);
        response.setMessage(item == null ? "Item not found" : "Item found");
        return response;
    }


    @GetMapping(value = "/all")
    public JsonResponse getAll(){
        JsonResponse response = new JsonResponse();
        response.setSuccess(true);
        response.setData(itemService.getAllItems());
        response.setMessage("Success");
        return response;
    }

}