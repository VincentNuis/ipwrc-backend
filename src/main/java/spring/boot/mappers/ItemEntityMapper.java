package spring.boot.mappers;

import org.springframework.stereotype.Component;
import spring.boot.data.ItemEntity;
import spring.boot.model.ItemModel;

@Component
public class ItemEntityMapper implements EntityMapper<ItemEntity, ItemModel> {
    public ItemModel map(ItemEntity entity){
        return new ItemModel(
                entity.getItemName(),
                entity.getCategory(),
                entity.getPrice(),
                entity.getImage()
        );
    }

    @Override
    public ItemEntity reverseMap(ItemModel itemModel) {
        return new ItemEntity(
                itemModel.getItemName(),
                itemModel.getCategory(),
                itemModel.getPrice(),
                itemModel.getImage()
        );
    }
}
