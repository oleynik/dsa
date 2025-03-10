package ua.infinity.dsa.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of the {@link Sorting} interface using the Bubble Sort algorithm.
 * Bubble Sort is generally inefficient for large datasets due to its O(n<sup>2</sup>) time complexity.
 *
 * @author Alex Oliinyk
 */
public final class BubbleSort implements Sorting {

    @Override
    public <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int n=array.length-1;n>0;n--) {
            for (int i = 0; i < n; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }
        for (int n=list.size()-1;n>0;n--) {
            for (int i = 0; i < n; i++) {
                if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
