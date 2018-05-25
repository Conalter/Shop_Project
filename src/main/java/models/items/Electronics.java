package models.items;

public class Electronics extends Item {

    private String voltage;

    public Electronics(String name, int price, String description, int quantity, String voltage) {
        super(name, price, description, quantity);
        this.voltage = voltage;
    }

    public Electronics() {
    }

    public Electronics(String voltage) {
        this.voltage = voltage;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }
}
