package ua.infinity.dsa.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;

/**
 * <p>The implementation of {@link Sorting} interface using <b>Quick Sort</b> algorithm.
 *
 * <p>The characteristics of the <b>Quick Sort</b>:
 * <ul>
 *     <li><b>Time Complexity</b>: O(n*log(n))</li>
 *     <li><b>Space Complexity</b>: O(1).</li>
 *     <li><b>Stability</b>: Not Stable.</li>
 *     <li><b>In-Place or Not</b>: No.</li>
 *     <li><b>Comparison-Based or Not</b>: Yes.</li>
 * </ul>
 *
 * @author Alex Oliinyk
 */
public class QuickSort implements Sorting {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }
        int length = array.length;
        T pivot = array[length-1];
        int tpIndex = -1;
        for (int i=0;i<length;i++) {
            if (comparator.compare(array[i], pivot) > 0) {
                continue;
            }
            tpIndex++;
            if (tpIndex < i) {
                T temp = array[i];
                array[i] = array[tpIndex];
                array[tpIndex] = temp;
            }
        }
        T[] left = (T[]) Array.newInstance(Comparable.class, tpIndex);
        T[] right = (T[]) Array.newInstance(Comparable.class, length-tpIndex-1);
        System.arraycopy(array, 0, left, 0, tpIndex);
        System.arraycopy(array, tpIndex+1, right, 0, length-tpIndex-1);
        sort(left, comparator);
        sort(right, comparator);
        System.arraycopy(left, 0, array, 0, left.length);
        System.arraycopy(right, 0, array, tpIndex+1, right.length);
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = list.size();
        T pivot = list.getLast();
        int tpIndex = -1;
        for (int i=0;i<size;i++) {
            if (comparator.compare(list.get(i), pivot) > 0) {
                continue;
            }
            tpIndex++;
            if (tpIndex < i) {
                T temp = list.get(i);
                list.set(i, list.get(tpIndex));
                list.set(tpIndex, temp);
            }
        }
        sort(list.subList(0, tpIndex), comparator);
        sort(list.subList(tpIndex+1, size), comparator);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
