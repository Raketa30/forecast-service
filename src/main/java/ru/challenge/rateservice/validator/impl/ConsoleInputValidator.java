package ru.challenge.rateservice.validator.impl;

import ru.challenge.rateservice.validator.Validator;

public class ConsoleInputValidator implements Validator<String> {

    public boolean isValid(String data) {
        if (data == null || data.isEmpty()) {
            return false;
        }
        return data.matches("RATE\\s+(USD|TRY|EUR)\\s+(WEEK|TOMORROW)");
    }
}
