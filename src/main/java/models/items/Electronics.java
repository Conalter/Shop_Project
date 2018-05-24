package models.items;

public class Electronics extends Item {

    private int voltage;

    public Electronics(String name, int price, String description, int quantity, int voltage) {
        super(name, price, description, quantity);
        this.voltage = voltage;
    }

    public Electronics() {
    }

    public Electronics(int voltage) {
        this.voltage = voltage;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
}
