package ua.infinity.dsa.algorithms.sorting.converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.Arrays;

/**
 * @author Alex Oliinyk
 */
public class ArrayToListConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (source == null) {
            return null;
        }
        if (!source.getClass().isArray()) {
            return source;
        }
        return Arrays.asList((Object[])source);
    }
}
