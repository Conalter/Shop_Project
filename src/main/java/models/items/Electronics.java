package models.items;

import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electronics" )
public class Electronics extends Item {

    private String voltage;

    public Electronics(String name, double price, String description, String voltage) {
        super(name, price, description);
        this.voltage = voltage;
    }

    public Electronics() {
    }

    public Electronics(String voltage) {
        this.voltage = voltage;
    }

    @Column(name = "voltage")
    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }
}
