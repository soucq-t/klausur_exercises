package domain;

import lombok.NonNull;

/**
 * Ein Angebot für einen bestimmten Artikel zu einem bestimmten Preis
 */
public record Offer(int quantity, @NonNull Price price) {

    public Offer {
    }
}
