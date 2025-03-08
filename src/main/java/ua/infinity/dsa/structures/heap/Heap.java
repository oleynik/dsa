package ua.infinity.dsa.structures.heap;

/**
 * A generic heap interface for managing comparable elements.
 * This interface defines the basic operations for a heap data structure,
 * allowing elements to be added, removed, and inspected while maintaining
 * the heap property.
 *
 * @param <T> The type of elements stored in the heap.  Must implement the
 *            {@link Comparable} interface for ordering.
 *
 * @author Alex Oliinyk
 */
public interface Heap<T extends Comparable<T>> {

    /**
     * Adds a new item to the heap.
     *
     * @param item The item to add to the heap.  Must not be null.
     * @throws NullPointerException if {@code item} is null.
     */
    void push(T item);

    /**
     * Removes and returns the element at the root of the heap (the minimum or
     * maximum element, depending on the heap type).
     *
     * @return The element at the root of the heap, or {@code null} if the heap is empty.
     */
    T pop();

    /**
     * Returns the element at the root of the heap (the minimum or maximum element,
     * depending on the heap type) without removing it.
     *
     * @return The element at the root of the heap, or {@code null} if the heap is empty.
     */
    T peek();

    /**
     * Returns the number of elements currently in the heap.
     *
     * @return The number of elements in the heap.
     */
    int size();
}
