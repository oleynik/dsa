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
public class IsAscSortedArrayProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Sorting> algorithms = List.of(
                new BubbleSort()
        );
        return algorithms.stream()
                .flatMap(algorithm -> Stream.of(
                        Arguments.of(algorithm, null, false),
                        Arguments.of(algorithm, new Integer[0], true),
                        Arguments.of(algorithm, new Integer[] {1}, true),
                        Arguments.of(algorithm, new Integer[] {1, 2}, true),
                        Arguments.of(algorithm, new Integer[] {2, 1}, false),
                        Arguments.of(algorithm, new Integer[] {1, 1}, true),
                        Arguments.of(algorithm, new Integer[] {1, 2, 3},  true),
                        Arguments.of(algorithm, new Integer[] {1, 3, 2},  false),
                        Arguments.of(algorithm, new Integer[] {2, 1, 3},  false),
                        Arguments.of(algorithm, new Integer[] {2, 3, 1},  false),
                        Arguments.of(algorithm, new Integer[] {3, 1, 2},  false),
                        Arguments.of(algorithm, new Integer[] {3, 2, 1},  false),
                        Arguments.of(algorithm, new Integer[] {1, 2, 3, 4, 5}, true),
                        Arguments.of(algorithm, new Integer[] {3, 2, 1, 5, 4}, false),
                        Arguments.of(algorithm, new Integer[] {5, 2, 4, 3, 1}, false),
                        Arguments.of(algorithm, new String[] {"a"}, true),
                        Arguments.of(algorithm, new String[] {"a", "aa"}, true),
                        Arguments.of(algorithm, new String[] {"aa", "a"}, false),
                        Arguments.of(algorithm, new String[] {"aa", "ab"}, true),
                        Arguments.of(algorithm, new String[] {"ab", "aa"}, false),
                        Arguments.of(algorithm, new String[] {"ab", "ab"}, true),
                        Arguments.of(algorithm, new String[] {"a", "aa", "aaa"}, true),
                        Arguments.of(algorithm, new String[] {"a", "aaa", "aa"}, false),
                        Arguments.of(algorithm, new String[] {"aa", "a", "aaa"}, false),
                        Arguments.of(algorithm, new String[] {"aa", "aaa", "a"}, false),
                        Arguments.of(algorithm, new String[] {"aaa", "a", "aa"}, false),
                        Arguments.of(algorithm, new String[] {"aaa", "aa", "a"}, false)
                ));
    }
}
