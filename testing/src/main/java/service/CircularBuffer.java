package service;

import java.util.Optional;

public interface CircularBuffer<T> {

    /**
     * Adds an element into the buffer.
     *
     * @param element Element to save
     */
    void push(T element);

    /**
     * Returns the oldest existing element
     *
     * @return the oldest existing element, wrapped in an Optional
     */
    Optional<T> pop();
}
