import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    private static final Map<String, Item> WAREHOUSE = new HashMap<>();

    static {
        WAREHOUSE.put("A", new Item("A", 1.25, 3, 3.00));
        WAREHOUSE.put("B", new Item("B", 4.25));
        WAREHOUSE.put("C", new Item("C", 1.00, 6, 5.00));
        WAREHOUSE.put("D", new Item("D", 0.75));
    }

    public static double calculateTotalCost(String basket) {
        String filteredBasket = filterBasketFromUnknownProducts(basket);
        if (checkIfBasketIsNotEmpty(filteredBasket)) {
            Map<String, Long> productsAmount = Arrays.stream(filteredBasket.split(""))
                    .map(WAREHOUSE::get)
                    .collect(Collectors.groupingBy(Item::getItem,
                            Collectors.mapping(Item::getItem, Collectors.counting())));

            return productsAmount.entrySet().stream()
                    .mapToDouble(p -> WAREHOUSE.get(p.getKey()).getPrice(p.getValue()))
                    .sum();
        } else {
            return 0;
        }
    }

    private static String filterBasketFromUnknownProducts(String basket) {
        if (checkIfBasketIsNotEmpty(basket)) {
            return Arrays.stream(basket.toUpperCase(Locale.ROOT)
                            .split(""))
                    .filter(WAREHOUSE::containsKey)
                    .collect(Collectors.joining());
        } else {
            throw new RuntimeException("Помилка! Введіть код товару!");
        }
    }

    private static boolean checkIfBasketIsNotEmpty(String basket) {
        return basket != null && !basket.isEmpty();
    }
}
