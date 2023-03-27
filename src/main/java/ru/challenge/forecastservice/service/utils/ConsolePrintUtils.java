package ru.challenge.forecastservice.service.utils;

import ru.challenge.forecastservice.domain.ForecastData;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public class ConsolePrintUtils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    private ConsolePrintUtils() {
    }

    private static final DateTimeFormatter dateFormatter = ofPattern("eee dd.MM.yyyy", new Locale("ru"));

    public static void printBlue(String text) {
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

    public static void printPurple(String text) {
        System.out.println(ANSI_PURPLE + text + ANSI_RESET);
    }

    public static void print(List<ForecastData> forecastData) {
        forecastData.forEach(data -> System.out.printf("%s - %.2f%n", data.date().format(dateFormatter), data.price()));
        System.out.println();
    }
}
