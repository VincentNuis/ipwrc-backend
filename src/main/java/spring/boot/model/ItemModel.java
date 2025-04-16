package spring.boot.model;

public class ItemModel {

    private String itemName;
    private String category;
    private double price;
    private String image;

    public ItemModel(String itemName, String category, double price, String image){
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
