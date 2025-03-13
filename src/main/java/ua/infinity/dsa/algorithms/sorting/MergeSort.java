package ua.infinity.dsa.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>The implementation of {@link Sorting} interface using <b>Merge Sort</b> algorithm.
 *
 * <p>The characteristics of the <b>Merge Sort</b>:
 * <ul>
 *     <li><b>Time Complexity</b>: O(n*log(n))</li>
 *     <li><b>Space Complexity</b>: O(n).</li>
 *     <li><b>Stability</b>: Stable.</li>
 *     <li><b>In-Place or Not</b>: No; however, the input array/iterable will be sorted after the execution.</li>
 *     <li><b>Comparison-Based or Not</b>: Yes.</li>
 * </ul>
 *
 * @author Alex Oliinyk
 */
public class MergeSort implements Sorting {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }
        int length = array.length;
        int half = length / 2;
        T[] left = (T[]) Array.newInstance(Comparable.class, half);
        T[] right = (T[]) Array.newInstance(Comparable.class, length - half);
        System.arraycopy(array, 0, left, 0, half);
        System.arraycopy(array, half, right, 0, length - half);
        sort(left, comparator);
        sort(right, comparator);
        int lIndex = 0;
        int rIndex = 0;
        for (int i=0;i<length;i++) {
            T l = lIndex < left.length ? left[lIndex] : null;
            T r = rIndex < right.length ? right[rIndex] : null;
            if (l == null) {
                array[i] = r;
                rIndex++;
                continue;
            }
            if (r == null) {
                array[i] = l;
                lIndex++;
                continue;
            }
            if (comparator.compare(l, r) <= 0) {
                array[i] = l;
                lIndex++;
            } else {
                array[i] = r;
                rIndex++;
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }
        int size = list.size();
        int half = size / 2;
        List<T> left = new ArrayList<>(list.subList(0, half));
        List<T> right = new ArrayList<>(list.subList(half, size));
        sort(left, comparator);
        sort(right, comparator);
        int lIndex = 0;
        int rIndex = 0;
        for (int i=0;i<size;i++) {
            T l = lIndex < left.size() ? left.get(lIndex) : null;
            T r = rIndex < right.size() ? right.get(rIndex) : null;
            if (l == null) {
                list.set(i, r);
                rIndex++;
                continue;
            }
            if (r == null) {
                list.set(i, l);
                lIndex++;
                continue;
            }
            if (comparator.compare(l, r) <= 0) {
                list.set(i, l);
                lIndex++;
            } else {
                list.set(i, r);
                rIndex++;
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
