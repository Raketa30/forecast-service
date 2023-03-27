package ru.challenge.forecastservice.validator.impl;

import ru.challenge.forecastservice.validator.Validator;

public class ConsoleInputValidator implements Validator<String> {

    public boolean isValid(String data) {
        if (data == null || data.isEmpty()) {
            return false;
        }
        return data.matches("RATE\\s+(USD|TRY|EUR)\\s+(WEEK|TOMORROW)");
    }
}
