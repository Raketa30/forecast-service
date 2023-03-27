package ru.challenge.forecastservice.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.challenge.forecastservice.validator.impl.ConsoleInputValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleInputValidatorTest {

    private final ConsoleInputValidator validator = new ConsoleInputValidator();

    @ParameterizedTest
    @ValueSource(strings = {
            "RATE EUR WEEK",
            "RATE TRY TOMORROW",
            "RATE USD WEEK"
    })
    void shouldBeTrueWhenCorrectUpperCaseInput(String input) {
        assertTrue(validator.isValid(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "rate try tomorrow",
            "rate usd week",
            "some test"
    })
    void shouldBeFalseWhenWrongInput(String input) {
        assertFalse(validator.isValid(input));
    }
}