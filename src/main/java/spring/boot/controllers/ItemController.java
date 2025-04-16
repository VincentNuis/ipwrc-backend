package spring.boot.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.model.ItemModel;
import spring.boot.services.ItemService;

import java.util.Optional;

public class ItemController {
    private final ItemService service;
    public ItemController(@Qualifier("primary") ItemService itemService){
        this.service = itemService;
    }

    @GetMapping("/getItem/{itemName}")
    public ResponseEntity<ItemModel> getItem(@PathVariable String itemName){
        return ResponseEntity.of(Optional.of(service.getItem(itemName)));
    }

    @PostMapping("/addItem")
    public ResponseEntity<HttpStatus> createItem(@RequestBody ItemModel item){
        service.addItem(item);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/deleteItem/{itemName}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable String itemName){
        service.deleteItem(itemName);
        return ResponseEntity.noContent().build();
    }
}
