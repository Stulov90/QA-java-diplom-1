package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private Ingredient ingredient;
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] dataForIngredientTest() {
        return new Object[][]{
                {IngredientType.SAUCE, "Соус с шипами Антарианского плоскоходца", 88.0f},
                {IngredientType.FILLING, "Филе Люминесцентного тетраодонтимформа", 988.0f},
                {IngredientType.SAUCE, "Соус традиционный галактический", 15.0f},
                {IngredientType.FILLING, "Кристаллы марсианских альфа-сахаридов", 762.0f}
        };
    }

    @Test
    public void ingredientGetPriceTest() {
        assertEquals("Цена ингредиента не соответствует ожидаемой", price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void ingredientGetNameTest() {
        assertEquals("Название ингредиента не соответствует ожидаемому", name, ingredient.getName());
    }

    @Test
    public void ingredientGetTypeTest() {
        assertEquals("Тип ингредиента не соответствует ожидаемому", type, ingredient.getType());
    }
}
