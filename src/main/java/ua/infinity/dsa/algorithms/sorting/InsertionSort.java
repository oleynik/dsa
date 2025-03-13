package ua.infinity.dsa.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * <p>Implementation of the {@link Sorting} interface using the <b>Insertion Sort</b> algorithm.
 *
 * <p>The characteristics of the <b>Insertion Sort</b>:
 * <ul>
 *     <li><b>Time Complexity</b>: O(n<sup>2</sup>)</li>
 *     <li><b>Space Complexity</b>: O(n).</li>
 *     <li><b>Stability</b>: Stable.</li>
 *     <li><b>In-Place or Not</b>: Yes.</li>
 *     <li><b>Comparison-Based or Not</b>: Yes.</li>
 * </ul>
 *
 * @author Alex Oliinyk
 */
public class InsertionSort implements Sorting {

    @Override
    public <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i=0;i<array.length-1;i++) {
            for (int j=i+1;j>0;j--) {
                T current = array[j];
                if (comparator.compare(current, array[j-1]) >= 0) {
                    break;
                }
                array[j] = array[j-1];
                array[j-1] = current;
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i=0;i<list.size()-1;i++) {
            for (int j=i+1;j>0;j--) {
                T current = list.get(j);
                if (comparator.compare(current, list.get(j-1)) >= 0) {
                    break;
                }
                list.set(j, list.get(j-1));
                list.set(j-1, current);
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
