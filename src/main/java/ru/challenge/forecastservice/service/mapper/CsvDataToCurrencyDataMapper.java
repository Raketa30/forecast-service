package ru.challenge.forecastservice.service.mapper;

import ru.challenge.forecastservice.domain.CurrencyData;
import ru.challenge.forecastservice.domain.enums.Currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CsvDataToCurrencyDataMapper {

    private static final String SCV_DELIMITER = ";";
    private static final String SCV_PRICE_DELIMITER = ",";
    private static final String SCV_SPACE = " ";
    private static final String STANDARD_PRICE_DELIMITER = ".";
    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private CsvDataToCurrencyDataMapper() {
    }

    public static CurrencyData mapToCurrencyData(String csvLine) {
        var splitByCsvDelimiter = csvLine.split(SCV_DELIMITER);

        var nominal = getNominal(splitByCsvDelimiter[0], csvLine);
        var date = getDate(splitByCsvDelimiter[1], csvLine);
        var price = getPrice(splitByCsvDelimiter[2], nominal, csvLine);
        var currency = getCurrency(splitByCsvDelimiter[3], csvLine);

        return new CurrencyData(date, price, currency);
    }

    private static Currency getCurrency(String rawCurrency, String csvLine) {
        Currency currency = Currency.getByName(rawCurrency);
        if (currency == null) {
            throw new IllegalArgumentException("Не удалось прочитать валюту: " + csvLine);
        }
        return currency;
    }

    private static double getPrice(String rawPrice, int nominal, String csvLine) {
        try {
            var price = Double.parseDouble(rawPrice.replace(SCV_PRICE_DELIMITER, STANDARD_PRICE_DELIMITER));
            return price / nominal;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Не удалось прочитать стоимость: " + csvLine);
        }
    }

    private static LocalDate getDate(String rawDate, String csvLine) {
        try {
            return LocalDate.parse(rawDate, CSV_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Не удалось прочитать дату: " + csvLine);
        }
    }

    private static int getNominal(String rawNominal, String csvLine) {
        try {
            return Integer.parseInt(rawNominal.replace(SCV_SPACE, ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Не удалось прочитать номинал: " + csvLine);
        }
    }
}
