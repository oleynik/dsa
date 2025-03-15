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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alex Oliinyk
 */
public class MaxIntegerArrayBasedHeapTest {

    private ArrayBasedHeap<Integer> heap;

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of(1), List.of(1)),
                Arguments.of(List.of(1, 2), List.of(2, 1)),
                Arguments.of(List.of(2, 1), List.of(2, 1)),
                Arguments.of(List.of(2, 1, 3), List.of(3, 1, 2)),
                Arguments.of(List.of(4, 3, 2, 1), List.of(4, 3, 2, 1)),
                Arguments.of(List.of(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0), List.of(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
        );
    }

    private static Stream<Arguments> testEquals() {
        Heap<Integer> h = ArrayBasedHeap.maxHeap();
        h.push(1);
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(ArrayBasedHeap.maxHeap(), true),
                Arguments.of(ArrayBasedHeap.minHeap(), true),
                Arguments.of(new ArrayBasedHeap<String>(2, Comparator.naturalOrder()), true),
                Arguments.of(new ArrayBasedHeap<String>(3, Comparator.reverseOrder()), true),
                Arguments.of(h, false),
                Arguments.of("Hello, World!", false),
                Arguments.of(new Object(), false)
        );
    }

    @BeforeEach
    public void beforeEach() {
        heap = ArrayBasedHeap.maxHeap();
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
    void push(List<Integer> list, List<Integer> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        assertEquals(expected.size(), heap.size());
        assertEquals(expected.toString(), heap.toString());
    }

    @MethodSource("data")
    @ParameterizedTest
    void pop(List<Integer> list, List<Integer> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        List<Integer> list1 = expected.stream().sorted(Comparator.reverseOrder()).toList();
        for (Integer exp : list1) {
            assertEquals(exp, heap.pop());
        }
        assertEquals(0, heap.size());
    }

    @MethodSource("data")
    @ParameterizedTest
    void peek(List<Integer> list, List<Integer> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);

        Integer first = expected.isEmpty() ? null : expected.getFirst();
        assertEquals(first, heap.peek());
        assertEquals(first, heap.peek()); // To make sure that does not remove
        assertEquals(expected.size(), heap.size());
    }

    @MethodSource("data")
    @ParameterizedTest
    void size(List<Integer> list, List<Integer> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        assertEquals(expected.size(), heap.size());
    }

    @MethodSource
    @ParameterizedTest
    void testEquals(Object that, boolean expected) {
        assertEquals(expected, heap.equals(that));
    }

    @Test
    void testNotEquals() {
        Heap<Integer> h = ArrayBasedHeap.maxHeap();
        h.push(1);
        h.push(2);
        h.push(3);
        System.out.println(h);
        heap.push(3);
        heap.push(2);
        heap.push(1);
        System.out.println(heap);
        assertNotEquals(heap, h);
    }

    @MethodSource("data")
    @ParameterizedTest
    void testHashCode(List<Integer> list) {
        list.forEach(heap::push);
        assertTrue(heap.hashCode() != 0);
    }

    @MethodSource("data")
    @ParameterizedTest
    void toString(List<Integer> list, List<Integer> expected) {
        assertEquals(0, heap.size());

        list.forEach(heap::push);
        assertEquals(expected.size(), heap.size());
        assertEquals(expected.toString(), heap.toString());
    }
}
