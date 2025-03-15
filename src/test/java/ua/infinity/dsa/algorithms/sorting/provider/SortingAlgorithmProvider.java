package ua.infinity.dsa.algorithms.sorting.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ua.infinity.dsa.algorithms.sorting.BubbleSort;
import ua.infinity.dsa.algorithms.sorting.InsertionSort;
import ua.infinity.dsa.algorithms.sorting.MergeSort;
import ua.infinity.dsa.algorithms.sorting.QuickSort;
import ua.infinity.dsa.algorithms.sorting.SelectionSort;

import java.util.stream.Stream;

/**
 * @author Alex Oliinyk
 */
public class SortingAlgorithmProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new BubbleSort()),
                Arguments.of(new SelectionSort()),
                Arguments.of(new InsertionSort()),
                Arguments.of(new MergeSort()),
                Arguments.of(new QuickSort())
        );
    }
}
