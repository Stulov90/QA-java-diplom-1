package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    public static final String SAUCE = "SAUCE";
    public static final String FILLING = "FILLING";

    @Test
    public void typeSauceTest() {
        assertEquals("Строка SAUCE не соответствует ожидаемой", SAUCE, IngredientType.SAUCE.toString());
    }

    @Test
    public void typeFillingTest() {
        assertEquals("Строка FILLING не соответствует ожидаемой", FILLING, IngredientType.FILLING.toString());
    }
}
