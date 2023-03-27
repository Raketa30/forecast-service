package ru.challenge.forecastservice.domain.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum Currency {
    EUR("Евро"),
    TRY("Турецкая лира"),
    USD("Доллар США");

    private final String name;
    private static final Map<String, Currency> currencyMap = new HashMap<>();

    Currency(String name) {
        this.name = name;
    }

    static {
        Arrays.stream(Currency.values())
                .forEach(currency -> currencyMap.put(currency.getName(), currency));
    }

    public String getName() {
        return name;
    }

    public static Currency getByName(String name) {
        return currencyMap.get(name);
    }

    public static String toInlineValues() {
        return Arrays.stream(Currency.values())
                .map(Currency::name)
                .collect(Collectors.joining("|"));
    }
}
