package models.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

/**
 * Validation to validate properties
 * @param <T>
 */
public interface Validation<T> extends Function<T, Boolean> {
    Validation<String> isNullOrEmptyString = input -> input == null || input.isEmpty();
    Validation<String> isValidPgiString = input -> !input.matches("[a-zA-Z]\\w{4,9}");
    Validation<String> isValidDate = input -> {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException dte) {
            return false;
        }

    };
}
