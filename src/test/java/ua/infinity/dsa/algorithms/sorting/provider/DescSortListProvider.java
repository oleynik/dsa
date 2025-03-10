package ua.infinity.dsa.algorithms.sorting.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ua.infinity.dsa.algorithms.sorting.BubbleSort;
import ua.infinity.dsa.algorithms.sorting.Sorting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Alex Oliinyk
 */
public class DescSortListProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Sorting> algorithms = List.of(
                new BubbleSort()
        );
        return algorithms.stream()
                .flatMap(algorithm -> Stream.of(
                        Arguments.of(algorithm, null, null),
                        Arguments.of(algorithm, Arrays.asList(), Arrays.asList()),
                        Arguments.of(algorithm, Arrays.asList(1), Arrays.asList(1)),
                        Arguments.of(algorithm, Arrays.asList(1, 2), Arrays.asList(2, 1)),
                        Arguments.of(algorithm, Arrays.asList(2, 1), Arrays.asList(2, 1)),
                        Arguments.of(algorithm, Arrays.asList(1, 1), Arrays.asList(1, 1)),
                        Arguments.of(algorithm, Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(1, 3, 2), Arrays.asList(3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(2, 1, 3), Arrays.asList(3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(2, 3, 1), Arrays.asList(3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(3, 2, 1), Arrays.asList(3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(5, 4, 3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(3, 2, 1, 5, 4), Arrays.asList(5, 4, 3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList(5, 2, 4, 3, 1), Arrays.asList(5, 4, 3, 2, 1)),
                        Arguments.of(algorithm, Arrays.asList("a"), Arrays.asList("a")),
                        Arguments.of(algorithm, Arrays.asList("a", "aa"), Arrays.asList("aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("aa", "a"), Arrays.asList("aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("aa", "ab"), Arrays.asList("ab", "aa")),
                        Arguments.of(algorithm, Arrays.asList("ab", "aa"), Arrays.asList("ab", "aa")),
                        Arguments.of(algorithm, Arrays.asList("ab", "ab"), Arrays.asList("ab", "ab")),
                        Arguments.of(algorithm, Arrays.asList("a", "aa", "aaa"), Arrays.asList("aaa", "aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("a", "aaa", "aa"), Arrays.asList("aaa", "aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("aa", "a", "aaa"), Arrays.asList("aaa", "aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("aa", "aaa", "a"), Arrays.asList("aaa", "aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("aaa", "a", "aa"), Arrays.asList("aaa", "aa", "a")),
                        Arguments.of(algorithm, Arrays.asList("aaa", "aa", "a"), Arrays.asList("aaa", "aa", "a"))
                ));
    }
}
