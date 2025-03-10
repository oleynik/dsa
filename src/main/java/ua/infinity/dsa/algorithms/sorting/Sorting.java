package ua.infinity.dsa.algorithms.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for sorting arrays and lists of {@link Comparable} elements.
 *
 * @author Alex Oliinyk
 */
public interface Sorting {

    /**
     * Sorts the given array using the natural ordering of its elements.
     *
     * @param array that needs to be sorted.
     * @param <T> The type of elements in the array, which must implement {@link Comparable}.
     */
    default <T extends Comparable<T>> void sort(T[] array) {
        sort(array, Comparator.naturalOrder());
    }

    /**
     * Sorts the given list using the natural ordering of its elements.
     *
     * @param list that needs to be sorted.
     * @param <T> The type of elements in the list, which must implement {@link Comparable}.
     */
    default <T extends Comparable<T>> void sort(List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using the provided comparator.
     *
     * @param array that needs to be sorted.
     * @param comparator that must be used for comparison of the elements.
     * @param <T> type of elements in the array, which must implement {@link Comparable}.
     * @throws NullPointerException if the {@code comparator} is {@code null}.
     */
    <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator);

    /**
     * Sorts the given list using the provided comparator.
     *
     * @param list that needs to be sorted.
     * @param comparator that must be used for comparison of the elements.
     * @param <T> type of elements in the list, which must implement {@link Comparable}.
     * @throws NullPointerException if the {@code comparator} is {@code null}.
     */
    <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator);

    /**
     * <p>Checks if the given array is sorted in ascending order according to the natural ordering
     * of its elements.  The elements of the array must implement the {@link Comparable} interface.
     *
     * <p>The {@code null} value will be treated as unsorted array.
     *
     * @param array to check if it is sorted or not.
     * @param <T> The type of elements in the array, which must implement {@link Comparable}.
     * @return {@code true} if the array is sorted according to natural ordering,
     * {@code false} otherwise.
     */
    default <T extends Comparable<T>> boolean isSorted(T[] array) {
        return isSorted(array, Comparator.naturalOrder());
    }

    /**
     * <p>Checks if the given array is sorted according to the provided {@code comparator}. The elements of the array must
     * implement the {@link Comparable} interface.
     *
     * <p>The {@code null} value will be treated as unsorted array.
     *
     * @param array to check if it is sorted or not.
     * @param <T> The type of elements in the array, which must implement {@link Comparable}.
     * @return {@code true} if the array is sorted according to the {@code comparator},
     * {@code false} otherwise.
     * @throws NullPointerException if {@code comparator} is {@code null}.
     */
    default <T extends Comparable<T>> boolean isSorted(T[] array, Comparator<T> comparator) {
        if (array == null) {
            return false;
        }
        int length = array.length;
        if (length < 2) {
            return true;
        }
        for (int i = 0; i < length-1; i++) {
            if (comparator.compare(array[i], array[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the given iterable object is sorted in ascending order according to the natural ordering
     * of its elements.  The elements of the iterable object must implement the {@link Comparable} interface.
     *
     * <p>The {@code null} value is treated as unsorted iterable object.
     *
     * @param iterable to check if the elements inside is sorted or not.
     * @param <T> The type of elements in the iterable object, which must implement {@link Comparable}.
     * @return {@code true} if the elements in the iterable object is sorted according to natural ordering,
     * {@code false} otherwise.
     * @throws NullPointerException if {@code comparator} is {@code null}.
     */
    default <T extends Comparable<T>> boolean isSorted(Iterable<T> iterable) {
        return isSorted(iterable, Comparator.naturalOrder());
    }

    /**
     * <p>Checks if the given iterable object is sorted according to the provided {@code comparator}. The elements of the
     * iterable object must implement the {@link Comparable} interface.
     *
     * <p>The {@code null} value is treated as unsorted iterable object.
     *
     * @param iterable to check if the elements inside is sorted or not.
     * @param <T> The type of elements in the iterable object, which must implement {@link Comparable}.
     * @return {@code true} if the elements in the iterable object is sorted, {@code false} otherwise.
     */
    default <T extends Comparable<T>> boolean isSorted(Iterable<T> iterable, Comparator<T> comparator) {
        if (iterable == null) {
            return false;
        }
        Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T prev = null;
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (prev == null) {
                prev = current;
                continue;
            }
            if (comparator.compare(prev, current) > 0) {
                return false;
            }
            prev = current;
        }
        return true;
    }
}
