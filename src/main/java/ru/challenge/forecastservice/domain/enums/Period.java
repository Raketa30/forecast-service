package ru.challenge.forecastservice.domain.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Period {
    TOMORROW(1),
    WEEK(7);

    private final int days;

    Period(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public static String toInlineValues() {
        return Arrays.stream(Period.values())
                .map(Period::name)
                .collect(Collectors.joining("|"));
    }
}
