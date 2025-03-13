package ua.infinity.dsa.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.infinity.dsa.algorithms.sorting.converter.ArrayToListConverter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test to verify implementation of Stability characteristic of Soring algorithms.
 *
 * @author Alex Oliinyk
 */
class StabilityTest {

    private record IndexedValue(int index, int value) implements Comparable<IndexedValue> {
            static IndexedValue of(int index, int value) {
                return new IndexedValue(index, value);
            }

        @Override
            public int compareTo(IndexedValue that) {
                return this.value - that.value;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (!(obj instanceof IndexedValue(int index1, int value1))) {
                    return false;
                }
                return this.index == index1 && this.value == value1;
            }

            @Override
            public String toString() {
                return "{" + index + "|" + value + "}";
            }
        }

    private static Stream<Sorting> algorithms() {
        return Stream.of(
                new BubbleSort(),
                new InsertionSort()
        );
    }

    private static Stream<Arguments> straightData() {
        return algorithms().flatMap(algorithm -> Stream.of(
                Arguments.of(algorithm,
                        new IndexedValue[] {IndexedValue.of(1, 1), IndexedValue.of(2, 1)},
                        new IndexedValue[] {IndexedValue.of(1, 1), IndexedValue.of(2, 1)}),
                Arguments.of(algorithm,
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 2),
                                IndexedValue.of(3, 2),
                                IndexedValue.of(4, 3),
                                IndexedValue.of(5, 3)
                        },
                        new IndexedValue[] {
                                IndexedValue.of(4, 3),
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 2),
                                IndexedValue.of(5, 3),
                                IndexedValue.of(3, 2)}),
                Arguments.of(algorithm,
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 1),
                                IndexedValue.of(3, 1),
                                IndexedValue.of(4, 1),
                                IndexedValue.of(5, 1)},
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 1),
                                IndexedValue.of(3, 1),
                                IndexedValue.of(4, 1),
                                IndexedValue.of(5, 1)}),
                Arguments.of(algorithm,
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 2),
                                IndexedValue.of(3, 3),
                                IndexedValue.of(4, 4),
                                IndexedValue.of(5, 5)},
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 2),
                                IndexedValue.of(3, 3),
                                IndexedValue.of(4, 4),
                                IndexedValue.of(5, 5)})
        ));
    }

    private static Stream<Arguments> reversedData() {
        return algorithms().flatMap(algorithm -> Stream.of(
                Arguments.of(algorithm,
                        new IndexedValue[] {IndexedValue.of(1, 1), IndexedValue.of(2, 1)},
                        new IndexedValue[] {IndexedValue.of(1, 1), IndexedValue.of(2, 1)}),
                Arguments.of(algorithm,
                        new IndexedValue[] {
                                IndexedValue.of(1, 3),
                                IndexedValue.of(2, 3),
                                IndexedValue.of(3, 2),
                                IndexedValue.of(4, 2),
                                IndexedValue.of(5, 1)
                        },
                        new IndexedValue[] {
                                IndexedValue.of(3, 2),
                                IndexedValue.of(1, 3),
                                IndexedValue.of(2, 3),
                                IndexedValue.of(5, 1),
                                IndexedValue.of(4, 2)}),
                Arguments.of(algorithm,
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 1),
                                IndexedValue.of(3, 1),
                                IndexedValue.of(4, 1),
                                IndexedValue.of(5, 1)},
                        new IndexedValue[] {
                                IndexedValue.of(1, 1),
                                IndexedValue.of(2, 1),
                                IndexedValue.of(3, 1),
                                IndexedValue.of(4, 1),
                                IndexedValue.of(5, 1)}),
                Arguments.of(algorithm,
                        new IndexedValue[] {
                                IndexedValue.of(1, 5),
                                IndexedValue.of(2, 4),
                                IndexedValue.of(3, 3),
                                IndexedValue.of(4, 2),
                                IndexedValue.of(5, 1)},
                        new IndexedValue[] {
                                IndexedValue.of(1, 5),
                                IndexedValue.of(2, 4),
                                IndexedValue.of(3, 3),
                                IndexedValue.of(4, 2),
                                IndexedValue.of(5, 1)})
        ));
    }

    @MethodSource("straightData")
    @ParameterizedTest
    void sortArray(Sorting algorithm, IndexedValue[] expected, IndexedValue[] array) {
        algorithm.sort(array);
        assertArrayEquals(expected, array);
    }

    @MethodSource("straightData")
    @ParameterizedTest
    void sortArrayByComparator(Sorting algorithm, IndexedValue[] expected, IndexedValue[] array) {
        algorithm.sort(array, Comparator.naturalOrder());
        assertArrayEquals(expected, array);
    }

    @MethodSource("straightData")
    @ParameterizedTest
    void sortIterable(Sorting algorithm,
                      @ConvertWith(ArrayToListConverter.class) List<IndexedValue> expected,
                      @ConvertWith(ArrayToListConverter.class) List<IndexedValue> iterable) {
        algorithm.sort(iterable);
        assertEquals(expected, iterable);
    }

    @MethodSource("straightData")
    @ParameterizedTest
    void sortIterableByComparator(Sorting algorithm,
                      @ConvertWith(ArrayToListConverter.class) List<IndexedValue> expected,
                      @ConvertWith(ArrayToListConverter.class) List<IndexedValue> iterable) {
        algorithm.sort(iterable, Comparator.naturalOrder());
        assertEquals(expected, iterable);
    }

    @MethodSource("reversedData")
    @ParameterizedTest
    void sortArrayByReverseComparator(Sorting algorithm, IndexedValue[] expected, IndexedValue[] array) {
        algorithm.sort(array, Comparator.reverseOrder());
        assertArrayEquals(expected, array);
    }

    @MethodSource("reversedData")
    @ParameterizedTest
    void sortIterableByReverseComparator(Sorting algorithm,
                                  @ConvertWith(ArrayToListConverter.class) List<IndexedValue> expected,
                                  @ConvertWith(ArrayToListConverter.class) List<IndexedValue> iterable) {
        algorithm.sort(iterable, Comparator.reverseOrder());
        assertEquals(expected, iterable);
    }
}
