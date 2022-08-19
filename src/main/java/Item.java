import java.util.Objects;

public class Item {
    private final String item;
    private final double price;
    private final int promotionalAmount;
    private final double promotionalPrice;

    public Item(String code, double price, int promotionalAmount, double promotionalPrice) {
        this.item = code;
        this.price = price;
        this.promotionalAmount = promotionalAmount;
        this.promotionalPrice = promotionalPrice;
    }

    public Item(String code, double price) {
        this(code, price, 1, price);
    }

    public String getItem() {
        return item;
    }

    public double getPrice(long amount) {
        if (amount <= 0) {
            throw new RuntimeException("Помилка! Сума не може бути <= 0!");
        }

        if (amount < promotionalAmount) {
            return amount * price;
        } else {
            return (double) (amount / promotionalAmount) * promotionalPrice + (amount % promotionalAmount) * price;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return Double.compare(item.price, price) == 0 &&
                promotionalAmount == item.promotionalAmount &&
                Double.compare(item.promotionalPrice, promotionalPrice) == 0 &&
                Objects.equals(this.item, item.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, price, promotionalAmount, promotionalPrice);
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                ", price=" + price +
                ", promotionalAmount=" + promotionalAmount +
                ", promotionalPrice=" + promotionalPrice +
                '}';
    }
}
