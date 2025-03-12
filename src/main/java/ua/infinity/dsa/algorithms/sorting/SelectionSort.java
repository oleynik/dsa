package ua.infinity.dsa.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * <p>The implementation of {@link Sorting} interface using <b>Selection Sort</b> algorithm.
 *
 * <p>The characteristics of the <b>Selection Sort</b>:
 * <ul>
 *     <li><b>Time Complexity</b>: O(n<sup>2</sup>)</li>
 *     <li><b>Space Complexity</b>: O(1).</li>
 *     <li><b>Stability</b>: Not Stable.</li>
 *     <li><b>In-Place or Not</b>: Yes.</li>
 *     <li><b>Comparison-Based or Not</b>: Yes.</li>
 * </ul>
 *
 * @author Alex Oliinyk
 */
public final class SelectionSort implements Sorting {

    @Override
    public <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }
        int length = array.length;
        for (int i = 0; i< length -1; i++) {
            int index = i;
            for (int j=i+1;j<length;j++) {
                if (comparator.compare(array[index], array[j]) > 0) {
                    index = j;
                }
            }
            T temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }
        int length = list.size();
        for (int i=0;i<length-1;i++) {
            int index = i;
            for (int j=i+1;j<length;j++) {
                if (comparator.compare(list.get(index), list.get(j)) > 0) {
                    index = j;
                }
            }
            T temp = list.get(i);
            list.set(i, list.get(index));
            list.set(index, temp);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
