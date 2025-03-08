package ua.infinity.dsa.structures.heap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alex Oliinyk
 */
public class ArrayBasedHeap<T extends Comparable<T>> implements Heap<T> {

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("rawtypes")
    private static final Comparator DEFAULT_COMPARATOR = Comparator.naturalOrder();

    private T[] values;
    private int size;
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> ArrayBasedHeap<T> minHeap() {
        return new ArrayBasedHeap<T>(DEFAULT_CAPACITY, DEFAULT_COMPARATOR);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> ArrayBasedHeap<T> maxHeap() {
        return new ArrayBasedHeap<T>(DEFAULT_CAPACITY, DEFAULT_COMPARATOR.reversed());
    }

    @SuppressWarnings("unchecked")
    public ArrayBasedHeap(int capacity, Comparator<T> comparator) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The Heap capacity value must be positive: " + capacity);
        }
        this.comparator = Objects.requireNonNull(comparator);
        this.values = (T[]) Array.newInstance(Comparable.class, capacity);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int top = index / 2;
            T parent = this.values[top];
            T current = this.values[index];
            if (this.comparator.compare(parent, current) <= 0) {
                return;
            }
            this.values[top] = current;
            this.values[index] = parent;
            index = top;
        }
    }

    @SuppressWarnings("unchecked")
    public void push(T item) {
        Objects.requireNonNull(item);
        if ((size + 1) >= values.length) {
            // Add more space
            T[] newValues = (T[]) Array.newInstance(Comparable.class, 2 * values.length + 1);
            System.arraycopy(this.values, 0, newValues, 0, size);
            this.values = newValues;
        }
        this.values[size++] = item;
        heapifyUp(size-1);
    }

    private void heapifyDown() {
        int index = 0;
        while (true) {
            int l = 2 * index + 1;
            if (l >= size) {
                return;
            }
            T current = this.values[index];
            T left = this.values[l];
            if (comparator.compare(left, current) < 0) {
                this.values[index] = left;
                this.values[l] = current;
                index = l;
                continue;
            }
            int r = l + 1;
            if (r >= size) {
                return;
            }
            T right = this.values[r];
            if (comparator.compare(right, current) < 0) {
                this.values[r] = current;
                this.values[index] = right;
                index = r;
                continue;
            }
            return;
        }
    }

    public T pop() {
        if (size <= 0) {
            return null;
        }
        T result = this.values[0];
        this.values[0] = this.values[size-1];
        this.values[size-1] = null;
        size--;
        heapifyDown();
        return result;
    }

    public T peek() {
        return size > 0 ? this.values[0] : null;
    }

    public int size() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArrayBasedHeap<?> heap)) {
            return false;
        }

        if (!Objects.equals(this.size, heap.size)) {
            return false;
        }
        for (int i=0;i<this.size-1;i++) {
            if (!this.values[i].equals(heap.values[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.values);
    }

    @Override
    public String toString() {
        return Arrays
                .stream(this.values)
                .limit(this.size)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
