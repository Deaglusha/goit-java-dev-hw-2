import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item testItem;

    @BeforeEach
    public void init() {
        testItem = new Item("A", 1.25, 3, 3);
    }

    @Test
    public void testShouldGetPrice_happyPath() {
        double expected = 1.25;
        double actual = testItem.getPrice(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldGetPrice_whenAmountIsZero() {
        assertThrows(RuntimeException.class, () -> testItem.getPrice(0),
                "Помилка! Сума не може бути <= 0!");
    }

    @Test
    public void testShouldGetPrice_whenAmountIsLessThanZero() {
        assertThrows(RuntimeException.class, () -> testItem.getPrice(-2),
                "Помилка! Сума не може бути <= 0!");
    }

    @Test
    public void testShouldCompareItems_happyPath() {
        Item similar = new Item("A", 1.25, 3, 3);
        assertEquals(similar, testItem);
    }

    @Test
    public void testShouldCompareItems_differentItems() {
        Item another = new Item("B", 4.25);
        assertNotEquals(another, testItem);

        Item sameWithAnotherPrice = new Item("A", 1.5, 3, 3);
        assertNotEquals(sameWithAnotherPrice, testItem);

        Item sameWithAnotherPromotionalAmount = new Item("A", 1.25, 4, 3);
        assertNotEquals(sameWithAnotherPromotionalAmount, testItem);

        Item sameWithAnotherPromotionalPrice = new Item("A", 1.25, 3, 4);
        assertNotEquals(sameWithAnotherPromotionalPrice, testItem);
    }

    @Test
    public void testShouldCompareHashCode_happyPath() {
        Item linkToTestItem = testItem;
        assertEquals(linkToTestItem.hashCode(), testItem.hashCode());

        Item sameToTestItem = new Item("A", 1.25, 3, 3);
        assertEquals(sameToTestItem.hashCode(), testItem.hashCode());
    }

    @Test
    public void testShouldCompareHashCode_differentItem() {
        Item sameWithAnotherPrice = new Item("A", 1.24, 3, 3);
        assertNotEquals(sameWithAnotherPrice.hashCode(), testItem.hashCode());
    }
}
