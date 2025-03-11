package ua.infinity.dsa.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ua.infinity.dsa.algorithms.sorting.converter.ArrayToListConverter;
import ua.infinity.dsa.algorithms.sorting.provider.SortingAlgorithmProvider;
import ua.infinity.dsa.algorithms.sorting.provider.SortingTestDataProvider;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test that covers all implementation of {@link Sorting} interface.
 *
 * @author Alex Oliinyk
 */
class SortingTest {

    @ParameterizedTest
    @ArgumentsSource(SortingAlgorithmProvider.class)
    void sortArrayByNullComparator(Sorting algorithm) {
        assertThrows(NullPointerException.class, () -> algorithm.sort(new Integer[] {1, 2}, null));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingAlgorithmProvider.class)
    void sortListByNullComparator(Sorting algorithm) {
        assertThrows(NullPointerException.class, () -> algorithm.sort(Arrays.asList(1, 2), null));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingAlgorithmProvider.class)
    void isSortedArrayByNullComparator(Sorting algorithm) {
        assertThrows(NullPointerException.class, () -> algorithm.isSorted(new Integer[] {1, 2}, null));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingAlgorithmProvider.class)
    void isSortedIterableByNullComparator(Sorting algorithm) {
        assertThrows(NullPointerException.class, () -> algorithm.isSorted(Arrays.asList(1, 2), null));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void sortArray(Sorting algorithm, T[] expected, T[] array) {
        algorithm.sort(array);
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void sortList(Sorting algorithm, @ConvertWith(ArrayToListConverter.class) List<T> expected, @ConvertWith(ArrayToListConverter.class) List<T> list) {
        algorithm.sort(list);
        assertEquals(expected, list);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void sortArrayByComparator(Sorting algorithm, T[] expected, T[] array, Comparator<T> comparator) {
        algorithm.sort(array, comparator);
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void sortListByComparator(Sorting algorithm, @ConvertWith(ArrayToListConverter.class) List<T> expected, @ConvertWith(ArrayToListConverter.class) List<T> list, Comparator<T> comparator) {
        algorithm.sort(list, comparator);
        assertEquals(expected, list);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void isSortedArray(Sorting algorithm, boolean expected, T[] array) {
        assertEquals(expected, algorithm.isSorted(array));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void isSortedArrayByComparator(Sorting algorithm, boolean expected, T[] array, Comparator<T> comparator) {
        assertEquals(expected, algorithm.isSorted(array, comparator));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void isSortedIterable(Sorting algorithm, boolean expected, @ConvertWith(ArrayToListConverter.class) Iterable<T> iterable) {
        assertEquals(expected, algorithm.isSorted(iterable));
    }

    @ParameterizedTest
    @ArgumentsSource(SortingTestDataProvider.class)
    <T extends Comparable<T>> void isSortedIterableByComparator(Sorting algorithm, boolean expected, @ConvertWith(ArrayToListConverter.class) Iterable<T> iterable, Comparator<T> comparator) {
        assertEquals(expected, algorithm.isSorted(iterable, comparator));
    }
}