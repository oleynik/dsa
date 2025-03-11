package ua.infinity.dsa.algorithms.sorting.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import ua.infinity.dsa.algorithms.sorting.Sorting;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Alex Oliinyk
 */
public class SortingTestDataProvider extends SortingAlgorithmProvider {

    public static final String TAG_SORT = "sort";
    public static final String TAG_IS_SORTED = "isSorted";

    protected Stream<Arguments> arguments() {
        // The order of arguments are next:
        //  - boolean sorted
        //  - T[] sorted array
        //  - T[] array
        //  - Comparator<T>
        return Stream.of(
            Arguments.of(false, null, null, null),
            Arguments.of(true, new Integer[0], new Integer[0], null),
            Arguments.of(true, new Integer[] {1}, new Integer[] {1}, null),

            Arguments.of(true, new Integer[] {1, 1}, new Integer[] {1, 1}, null),
            Arguments.of(true, new Integer[] {1, 1}, new Integer[] {1, 1}, Comparator.naturalOrder()),
            Arguments.of(true, new Integer[] {1, 1}, new Integer[] {1, 1}, Comparator.reverseOrder()),

            Arguments.of(true, new Integer[] {1, 2}, new Integer[] {1, 2}, null),
            Arguments.of(true, new Integer[] {1, 2}, new Integer[] {1, 2}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {2, 1}, new Integer[] {1, 2}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2}, new Integer[] {2, 1}, null),
            Arguments.of(false, new Integer[] {1, 2}, new Integer[] {2, 1}, Comparator.naturalOrder()),
            Arguments.of(true, new Integer[] {2, 1}, new Integer[] {2, 1}, Comparator.reverseOrder()),

            Arguments.of(true, new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3}, null),
            Arguments.of(true, new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {3, 2, 1}, new Integer[] {1, 2, 3}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {1, 3, 2}, null),
            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {1, 3, 2}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {3, 2, 1}, new Integer[] {1, 3, 2}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {2, 1, 3}, null),
            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {2, 1, 3}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {3, 2, 1}, new Integer[] {2, 1, 3}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {2, 3, 1}, null),
            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {2, 3, 1}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {3, 2, 1}, new Integer[] {2, 3, 1}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {3, 1, 2}, null),
            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {3, 1, 2}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {3, 2, 1}, new Integer[] {3, 1, 2}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {3, 2, 1}, null),
            Arguments.of(false, new Integer[] {1, 2, 3}, new Integer[] {3, 2, 1}, Comparator.naturalOrder()),
            Arguments.of(true, new Integer[] {3, 2, 1}, new Integer[] {3, 2, 1}, Comparator.reverseOrder()),

            Arguments.of(true, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 2, 3, 4, 5}, null),
            Arguments.of(true, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 2, 3, 4, 5}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {5, 4, 3, 2, 1}, new Integer[] {1, 2, 3, 4, 5}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 4, 3, 2, 5}, null),
            Arguments.of(false, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {1, 4, 3, 2, 5}, Comparator.naturalOrder()),
            Arguments.of(false, new Integer[] {5, 4, 3, 2, 1}, new Integer[] {1, 4, 3, 2, 5}, Comparator.reverseOrder()),

            Arguments.of(false, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {5, 4, 3, 2, 1}, null),
            Arguments.of(false, new Integer[] {1, 2, 3, 4, 5}, new Integer[] {5, 4, 3, 2, 1}, Comparator.naturalOrder()),
            Arguments.of(true, new Integer[] {5, 4, 3, 2, 1}, new Integer[] {5, 4, 3, 2, 1}, Comparator.reverseOrder())
        );
    }

    @Override
    public final Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // The Arguments order must be next:
        //  – Sorting algorithm (see algorithms() method)
        //  – Arguments (see arguments() method)
        boolean skipArrayExpectation = context.getTestMethod()
                .map(Method::getName)
                .map(name -> name.startsWith(TAG_IS_SORTED))
                .orElse(false);

        boolean skipBooleanExpectation = context.getTestMethod()
                .map(Method::getName)
                .map(name -> name.startsWith(TAG_SORT))
                .orElse(false);

        boolean skipComparator = context.getTestMethod()
                .map(Method::getParameterTypes)
                .map(params -> params[params.length-1])
                .map(param -> !Comparator.class.isAssignableFrom(param))
                .orElse(false);

        return super.provideArguments(context)
                .map(Arguments::get)
                .map(objects -> (Sorting) objects[0])
                .flatMap(algorithm -> arguments()
                        .map(Arguments::get)
                        .filter(arguments -> (arguments[arguments.length - 1] == null) == skipComparator)
                        .map(arguments ->
                                Arguments.of(Stream.concat(
                                        Stream.of(algorithm),
                                        IntStream.range(0, arguments.length)
                                                .filter(i -> i != 0 || !skipBooleanExpectation)
                                                .filter(i -> i != 1 || !skipArrayExpectation)
                                                .mapToObj(i -> arguments[i])
                                ).toArray()))
                );
    }
}
