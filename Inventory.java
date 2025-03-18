public class Inventory {
    private String name;
    private String ID;
    private String category;
    private int quantity;
    private double price;
    public Inventory(String name, String ID, String category, int quantity, double price) {
        this.name = name;
        this.ID = ID;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
    public String getCategory() {
        return category;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String toString() {
        return name + " " + ID + " " + category + " " + quantity + " " + price;
    }
}