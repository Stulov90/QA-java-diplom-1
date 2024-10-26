package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1, ingredient2, ingredient3;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void burgerSetBunsTest() {
        burger.setBuns(bun);
        assertEquals("Название булочки не соответствует ожидаемому", bun.getName(), burger.bun.getName());
    }

    @Test
    public void burgerAddIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals("Количество ингредиентов не соответствует ожидаемому", 2, burger.ingredients.size());
        assertEquals("Ingredient1 не соответствует ожидаемому", ingredient1.getName(), burger.ingredients.get(0).getName());
        assertEquals("Ingredient2 не соответствует ожидаемому", ingredient2.getName(), burger.ingredients.get(1).getName());
    }

    @Test
    public void burgerRemoveIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        assertEquals("Количество ингредиентов не соответствует ожидаемому", 3, burger.ingredients.size());
        burger.removeIngredient(2);
        assertEquals("Количество ингредиентов не изменилось", 2, burger.ingredients.size());
        List<Ingredient> expectedIngredients = Arrays.asList(ingredient1, ingredient2);
        assertEquals("Ingredient3 всё ещё в составе бургера", expectedIngredients, burger.ingredients);
    }

    @Test
    public void burgerMoveIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        assertEquals("Количество ингредиентов не соответствует ожидаемому", 3, burger.ingredients.size());
        assertEquals("Ingredient1 занимает первую позицию в списке ингредиентов", ingredient1, burger.ingredients.get(0));
        assertEquals("Ingredient2 занимает вторую позицию в списке ингредиентов", ingredient2, burger.ingredients.get(1));
        assertEquals("Ingredient3 занимает третью позицию в списке ингредиентов", ingredient3, burger.ingredients.get(2));
        burger.moveIngredient(0, 2);
        assertEquals("Количество ингредиентов не должно измениться", 3, burger.ingredients.size());
        assertEquals("Ingredient2 занимает первую позицию в списке ингредиентов", ingredient2, burger.ingredients.get(0));
        assertEquals("Ingredient3 занимает вторую позицию в списке ингредиентов", ingredient3, burger.ingredients.get(1));
        assertEquals("Ingredient1 занимает третью позицию в списке ингредиентов", ingredient1, burger.ingredients.get(2));
    }

    @Test
    public void burgerGetPriceTest() {
        when(bun.getPrice()).thenReturn(1255.0f);
        when(ingredient1.getPrice()).thenReturn(500.0f);
        when(ingredient2.getPrice()).thenReturn(750.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = (1255.0f * 2) + 500.0f + 750.0f;
        assertEquals("Цена бургера не соответствует ожидаемой", expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void burgerGetReceiptTest() {
        when(bun.getName()).thenReturn("Краторная булка N-200i");
        when(bun.getPrice()).thenReturn(1255.0f);

        when(ingredient1.getName()).thenReturn("Соус с шипами Антарианского плоскоходца");
        when(ingredient1.getPrice()).thenReturn(88.0f);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredient2.getName()).thenReturn("Филе Люминесцентного тетраодонтимформа");
        when(ingredient2.getPrice()).thenReturn(988.0f);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %.6f%n",
                "Краторная булка N-200i",
                "sauce", "Соус с шипами Антарианского плоскоходца",
                "filling", "Филе Люминесцентного тетраодонтимформа",
                "Краторная булка N-200i",
                3586.0f);
        assertEquals("Чек не соответствует ожидаемому", expectedReceipt, burger.getReceipt());
        System.out.println(burger.getReceipt());
    }
}
