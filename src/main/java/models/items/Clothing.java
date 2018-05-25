package models.items;

public class Clothing extends Item {

    private Size size;

    public Clothing(String name, double price, String description, int quantity, Size size) {
        super(name, price, description, quantity);
        this.size = size;
    }

    public Clothing() {
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
