package domain;

import java.util.Objects;

/**
 * Ein konkreter Artikel im Einkaufskorb
 */
public record Item(Product product, int quantity) {

    public Item {
    }

    public Item(Product product) {
        this(product, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Item item = (Item) o;

        return Objects.equals(product, item.product);
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }
}
