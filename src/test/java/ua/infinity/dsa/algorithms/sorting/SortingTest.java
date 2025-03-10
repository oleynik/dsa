package ua.infinity.dsa.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ua.infinity.dsa.algorithms.sorting.provider.AscSortArrayProvider;
import ua.infinity.dsa.algorithms.sorting.provider.AscSortListProvider;
import ua.infinity.dsa.algorithms.sorting.provider.DescSortArrayProvider;
import ua.infinity.dsa.algorithms.sorting.provider.DescSortListProvider;
import ua.infinity.dsa.algorithms.sorting.provider.IsAscSortedArrayProvider;
import ua.infinity.dsa.algorithms.sorting.provider.IsAscSortedListProvider;
import ua.infinity.dsa.algorithms.sorting.provider.IsDescSortedArrayProvider;
import ua.infinity.dsa.algorithms.sorting.provider.IsDescSortedListProvider;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test that covers all implementation of {@link Sorting} interface.
 *
 * @author Alex Oliinyk
 */
class SortingTest {

    @ParameterizedTest
    @ArgumentsSource(AscSortArrayProvider.class)
    <T extends Comparable<T>> void sortArray(Sorting algorithm, T[] array, T[] expected) {
        algorithm.sort(array);
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @ArgumentsSource(AscSortListProvider.class)
    <T extends Comparable<T>> void sortList(Sorting algorithm, List<T> list, List<T> expected) {
        algorithm.sort(list);
        assertEquals(expected, list);
    }

    @ParameterizedTest
    @ArgumentsSource(DescSortArrayProvider.class)
    <T extends Comparable<T>> void sortArrayByComparator(Sorting algorithm, T[] array, T[] expected) {
        algorithm.sort(array, Comparator.reverseOrder());
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @ArgumentsSource(DescSortListProvider.class)
    <T extends Comparable<T>> void sortListByComparator(Sorting algorithm, List<T> list, List<T> expected) {
        algorithm.sort(list, Comparator.reverseOrder());
        assertEquals(expected, list);
    }

    @ParameterizedTest
    @ArgumentsSource(IsAscSortedArrayProvider.class)
    <T extends Comparable<T>> void isSortedArray(Sorting algorithm, T[] array, boolean expected) {
        assertEquals(expected, algorithm.isSorted(array));
    }

    @ParameterizedTest
    @ArgumentsSource(IsDescSortedArrayProvider.class)
    <T extends Comparable<T>> void isSortedArrayByComparator(Sorting algorithm, T[] array, boolean expected) {
        assertEquals(expected, algorithm.isSorted(array, Comparator.reverseOrder()));
    }

    @ParameterizedTest
    @ArgumentsSource(IsAscSortedListProvider.class)
    <T extends Comparable<T>> void isSortedIterable(Sorting algorithm, Iterable<T> iterable, boolean expected) {
        assertEquals(expected, algorithm.isSorted(iterable));
    }

    @ParameterizedTest
    @ArgumentsSource(IsDescSortedListProvider.class)
    <T extends Comparable<T>> void isSortedIterableByComparator(Sorting algorithm, Iterable<T> iterable, boolean expected) {
        assertEquals(expected, algorithm.isSorted(iterable, Comparator.reverseOrder()));
    }
}