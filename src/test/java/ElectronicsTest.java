import models.items.Electronics;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElectronicsTest {

    Electronics electronics;

    @Before
    public void before(){
        electronics = new Electronics("6v");
    }

    @Test
    public void hasVoltage(){
        assertEquals("6v", electronics.getVoltage());
    }
}
