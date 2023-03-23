package ru.challenge.rateservice.service.utils;

import ru.challenge.rateservice.domain.CurrencyRate;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.*;

public class ConsolePrintUtils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    private ConsolePrintUtils() {
    }

    private static final DateTimeFormatter dateFormatter = ofPattern("eee dd.MM.yyyy", new Locale("ru"));

    public static void print(String text) {
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

    public static void printHelp(String text) {
        System.out.println(ANSI_PURPLE + text + ANSI_RESET);
    }

    public static void print(List<CurrencyRate> currencyRates) {
        currencyRates.forEach(currencyRate -> System.out.printf("%s - %.2f%n", currencyRate.date().format(dateFormatter), currencyRate.price()));
        System.out.println();
    }
}
