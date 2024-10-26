package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private Bun bun;
    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters
    public static Object[][] dataForBunTest() {
        return new Object[][]{
                {"Флюоресцентная булка R2-D3", 988.0f},
                {"Краторная булка N-200i", 1255.0f}
        };
    }

    @Test
    public void bunGetNameTest() {
        assertEquals("Название булочки не соответствует ожидаемому", name, bun.getName());
    }

    @Test
    public void bunGetPriceTest() {
        assertEquals("Цена булочки не соответствует ожидаемому", price, bun.getPrice(), 0.001f);
    }
}
