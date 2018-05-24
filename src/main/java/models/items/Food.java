package models.items;

public class Food extends Item {

    private String date;

    public Food(String name, int price, String description, int quantity, String date) {
        super(name, price, description, quantity);
        this.date = date;
    }

    public Food(){
    }

    public Food(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
