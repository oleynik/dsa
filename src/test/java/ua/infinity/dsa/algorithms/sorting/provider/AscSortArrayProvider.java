package ua.infinity.dsa.algorithms.sorting.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ua.infinity.dsa.algorithms.sorting.BubbleSort;
import ua.infinity.dsa.algorithms.sorting.Sorting;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Alex Oliinyk
 */
public class AscSortArrayProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Sorting> algorithms = List.of(
                new BubbleSort()
        );
        return algorithms.stream()
                .flatMap(algorithm -> Stream.of(
                        Arguments.of(algorithm, null, null),
                        Arguments.of(algorithm, new Integer[0], new Integer[0]),
                        Arguments.of(algorithm, new Integer[] {1}, new Integer[] {1}),
                        Arguments.of(algorithm, new Integer[] {1, 2}, new Integer[] {1, 2}),
                        Arguments.of(algorithm, new Integer[] {2, 1}, new Integer[] {1, 2}),
                        Arguments.of(algorithm, new Integer[] {1, 1}, new Integer[] {1, 1}),
                        Arguments.of(algorithm, new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3}),
                        Arguments.of(algorithm, new Integer[] {1, 3, 2}, new Integer[] {1, 2, 3}),
                        Arguments.of(algorithm, new Integer[] {2, 1, 3}, new Integer[] {1, 2, 3}),
                        Arguments.of(algorithm, new Integer[] {2, 3, 1}, new Integer[] {1, 2, 3}),
                        Arguments.of(algorithm, new Integer[] {3, 1, 2}, new Integer[] {1, 2, 3}),
                        Arguments.of(algorithm, new Integer[] {3, 2, 1}, new Integer[] {1, 2, 3}),
                        Arguments.of(algorithm, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 2, 3, 4, 5}),
                        Arguments.of(algorithm, new Integer[] {3, 2, 1, 5, 4}, new Integer[] {1, 2, 3, 4, 5}),
                        Arguments.of(algorithm, new Integer[] {5, 2, 4, 3, 1}, new Integer[] {1, 2, 3, 4, 5}),
                        Arguments.of(algorithm, new String[] {"a"}, new String[] {"a"}),
                        Arguments.of(algorithm, new String[] {"a", "aa"}, new String[] {"a", "aa"}),
                        Arguments.of(algorithm, new String[] {"aa", "a"}, new String[] {"a", "aa"}),
                        Arguments.of(algorithm, new String[] {"aa", "ab"}, new String[] {"aa", "ab"}),
                        Arguments.of(algorithm, new String[] {"ab", "aa"}, new String[] {"aa", "ab"}),
                        Arguments.of(algorithm, new String[] {"ab", "ab"}, new String[] {"ab", "ab"}),
                        Arguments.of(algorithm, new String[] {"a", "aa", "aaa"}, new String[] {"a", "aa", "aaa"}),
                        Arguments.of(algorithm, new String[] {"a", "aaa", "aa"}, new String[] {"a", "aa", "aaa"}),
                        Arguments.of(algorithm, new String[] {"aa", "a", "aaa"}, new String[] {"a", "aa", "aaa"}),
                        Arguments.of(algorithm, new String[] {"aa", "aaa", "a"}, new String[] {"a", "aa", "aaa"}),
                        Arguments.of(algorithm, new String[] {"aaa", "a", "aa"}, new String[] {"a", "aa", "aaa"}),
                        Arguments.of(algorithm, new String[] {"aaa", "aa", "a"}, new String[] {"a", "aa", "aaa"})
                ));
    }
}
