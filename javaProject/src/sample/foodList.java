package sample;

public class foodList {
    private String foodName;
    private String price;

    public foodList(String foodName, String price) {
        this.foodName = foodName;
        this.price = price;
    }

    public foodList() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "foodList{" +
                "foodName='" + foodName + '\'' +
                ", price=" + price +
                '}';
    }
}
