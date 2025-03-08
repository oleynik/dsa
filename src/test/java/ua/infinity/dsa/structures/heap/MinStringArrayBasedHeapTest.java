package ua.infinity.dsa.structures.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alex Oliinyk
 */
class MinStringArrayBasedHeapTest {

    private ArrayBasedHeap<String> heap;

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of("1"), List.of("1")),
                Arguments.of(List.of("1", "2"), List.of("1", "2")),
                Arguments.of(List.of("2", "1"), List.of("1", "2")),
                Arguments.of(List.of("2", "1", "3"), List.of("1", "2", "3"))
        );
    }

    private static Stream<Arguments> testEquals() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(ArrayBasedHeap.minHeap(), true),
                Arguments.of(ArrayBasedHeap.maxHeap(), true),
                Arguments.of(new ArrayBasedHeap<String>(2, Comparator.naturalOrder()), true),
                Arguments.of(new ArrayBasedHeap<String>(3, Comparator.reverseOrder()), true),
                Arguments.of(new Object(), false),
                Arguments.of("Hello, World!", false)
        );
    }

    @BeforeEach
    void setUp() {
        heap = ArrayBasedHeap.minHeap();
    }

    @Test
    void testNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayBasedHeap<Integer>(-1, Comparator.naturalOrder()));
    }

    @Test
    void testNullComparator() {
        assertThrows(NullPointerException.class, () -> new ArrayBasedHeap<Integer>(0, null));
    }

    @Test
    void testPushNull() {
        assertThrows(NullPointerException.class, () -> heap.push(null));
    }

    @Test
    void testPopFromEmpty() {
        assertNull(heap.pop());
    }

    @Test
    void testPeekFromEmpty() {
        assertNull(heap.peek());
    }

    @MethodSource("data")
    @ParameterizedTest
    void push(List<String> list, List<String> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        assertEquals(expected.size(), heap.size());
        assertEquals(expected.toString(), heap.toString());
    }

    @MethodSource("data")
    @ParameterizedTest
    void peek(List<String> list, List<String> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        String first = expected.isEmpty() ? null : expected.getFirst();
        assertEquals(first, heap.peek());
        assertEquals(first, heap.peek()); // To make sure that does not remove
        assertEquals(list.size(), heap.size());
    }

    @MethodSource("data")
    @ParameterizedTest
    void pop(List<String> list, List<String> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        for (int i=0;i<expected.size();i++) {
            assertEquals(expected.get(i), heap.pop());
            assertEquals(expected.size()-i-1, heap.size());
        }
    }

    @MethodSource("data")
    @ParameterizedTest
    void size(List<String> list) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        assertEquals(list.size(), heap.size());
    }

    @MethodSource
    @ParameterizedTest
    void testEquals(Object that, boolean expected) {
        assertEquals(expected, heap.equals(that));
    }

    @MethodSource("data")
    @ParameterizedTest
    void testToString(List<String> list, List<String> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        assertEquals(expected.toString(), heap.toString());
    }
}
