import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasketTest {
    @Test
    public void testShouldCalculateTotalCost_happyPath() {
        String basket = "ABCDABA";
        double expected = 13.25;
        double actual = Basket.calculateTotalCost(basket);
        assertEquals(expected, actual);

        String basketWithLowerCaseLetters = "abdCAb";
        double expectedWithLowerCaseLetters = 12.75;
        double actualWithLowerCaseLetters = Basket.calculateTotalCost(basketWithLowerCaseLetters);
        assertEquals(expectedWithLowerCaseLetters, actualWithLowerCaseLetters);
    }

    @Test
    public void testShouldCalculateTotalCost_withEmptyBasket() {
        assertThrows(RuntimeException.class, () -> Basket.calculateTotalCost(null),
                "Помилка! Введіть код товару!");
        assertThrows(RuntimeException.class, () -> Basket.calculateTotalCost(""),
                "Помилка! Введіть код товару!");
    }

    @Test
    public void testShouldCalculateTotalCost_withUnknownProducts() {
        String basketWithUnknownCodes = "fknnf";
        double expectedWithUnknownCodes = 0;
        double actualWithUnknownCodes = Basket.calculateTotalCost(basketWithUnknownCodes);
        assertEquals(expectedWithUnknownCodes, actualWithUnknownCodes);

        String basketWithSpace = " ABC DA BA";
        double expectedWithSpace = 13.25;
        double actualWithSpace = Basket.calculateTotalCost(basketWithSpace);
        assertEquals(expectedWithSpace, actualWithSpace);

        String basketWithNumbers = "ABC58DABA25";
        double expectedWithNumbers = 13.25;
        double actualWithNumbers = Basket.calculateTotalCost(basketWithNumbers);
        assertEquals(expectedWithNumbers, actualWithNumbers);

        String basketWithCorrectAndUnknown = "ABCDABAGHN";
        double expectedWithCorrectAndUnknown = 13.25;
        double actualWithCorrectAndUnknown = Basket.calculateTotalCost(basketWithCorrectAndUnknown);
        assertEquals(expectedWithCorrectAndUnknown, actualWithCorrectAndUnknown);
    }
}
