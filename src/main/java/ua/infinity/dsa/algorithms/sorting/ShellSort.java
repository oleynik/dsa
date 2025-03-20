package ua.infinity.dsa.algorithms.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 <p>Implementation of the {@link Sorting} interface using the <b>Shell Sort</b> algorithm.
 *
 * <p>The characteristics of the <b>Shell Sort</b>:
 * <ul>
 *     <li><b>Time Complexity</b>: O(n<sup>2</sup>lg(n))</li>
 *     <li><b>Space Complexity</b>: O(1).</li>
 *     <li><b>Stability</b>: Not Stable.</li>
 *     <li><b>In-Place or Not</b>: Yes.</li>
 *     <li><b>Comparison-Based or Not</b>: Yes.</li>
 * </ul>
 *
 * @author Alex Oliinyk
 */
public class ShellSort implements Sorting {

    @Override
    public <T extends Comparable<T>> void sort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }
        int length = array.length;
        for (int gap=length/2;gap>=1;gap/=2) {
            for (int i=0;i<length;i+=gap) {
                for (int j=i-gap;j>=0;j-=gap) {
                    if (comparator.compare(array[j], array[j+gap]) > 0) {
                        T temp = array[j];
                        array[j] = array[j+gap];
                        array[j+gap] = temp;
                    }
                }
            }
        }
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = list.size();
        for (int gap=size/2;gap>=1;gap/=2) {
            for (int i=0;i<size;i+=gap) {
                for (int j=i-gap;j>=0;j-=gap) {
                    if (comparator.compare(list.get(j), list.get(j+gap)) > 0) {
                        T temp = list.get(j);
                        list.set(j, list.get(j+gap));
                        list.set(j+gap, temp);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
