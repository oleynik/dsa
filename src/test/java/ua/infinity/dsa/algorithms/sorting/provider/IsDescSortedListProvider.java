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
public class IsDescSortedListProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Sorting> algorithms = List.of(
                new BubbleSort()
        );
        return algorithms.stream()
                .flatMap(algorithm -> Stream.of(
                        Arguments.of(algorithm, null, false),
                        Arguments.of(algorithm, List.of(), true),
                        Arguments.of(algorithm, List.of(1), true),
                        Arguments.of(algorithm, List.of(1, 2), false),
                        Arguments.of(algorithm, List.of(2, 1), true),
                        Arguments.of(algorithm, List.of(1, 1), true),
                        Arguments.of(algorithm, List.of(1, 2, 3),  false),
                        Arguments.of(algorithm, List.of(1, 3, 2),  false),
                        Arguments.of(algorithm, List.of(2, 1, 3),  false),
                        Arguments.of(algorithm, List.of(2, 3, 1),  false),
                        Arguments.of(algorithm, List.of(3, 1, 2),  false),
                        Arguments.of(algorithm, List.of(3, 2, 1),  true),
                        Arguments.of(algorithm, List.of(1, 2, 3, 4, 5), false),
                        Arguments.of(algorithm, List.of(3, 2, 1, 5, 4), false),
                        Arguments.of(algorithm, List.of(5, 2, 4, 3, 1), false),
                        Arguments.of(algorithm, List.of(5, 4, 3, 2, 1), true),
                        Arguments.of(algorithm, List.of("a"), true),
                        Arguments.of(algorithm, List.of("a", "aa"), false),
                        Arguments.of(algorithm, List.of("aa", "a"), true),
                        Arguments.of(algorithm, List.of("aa", "ab"), false),
                        Arguments.of(algorithm, List.of("ab", "aa"), true),
                        Arguments.of(algorithm, List.of("ab", "ab"), true),
                        Arguments.of(algorithm, List.of("a", "aa", "aaa"), false),
                        Arguments.of(algorithm, List.of("a", "aaa", "aa"), false),
                        Arguments.of(algorithm, List.of("aa", "a", "aaa"), false),
                        Arguments.of(algorithm, List.of("aa", "aaa", "a"), false),
                        Arguments.of(algorithm, List.of("aaa", "a", "aa"), false),
                        Arguments.of(algorithm, List.of("aaa", "aa", "a"), true)
                ));
    }
}
