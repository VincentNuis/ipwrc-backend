package spring.boot.services;

import spring.boot.model.ItemModel;

public interface ItemService {
    ItemModel getItem(String itemName);
    void addItem(ItemModel item);
    void deleteItem(String itemName);
}
