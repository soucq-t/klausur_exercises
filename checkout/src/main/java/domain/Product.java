package domain;

import java.util.Objects;
import java.util.Set;

/**
 * Ein Produkt inklusive Angeboten
 */
public record Product(String description, Set<Offer> offers) {

    public Product(String description, Price price) {
        this(null, Set.of());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        return Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }
}
