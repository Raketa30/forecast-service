package ru.challenge.forecastservice.service.utils;

import ru.challenge.forecastservice.domain.CurrencyData;
import ru.challenge.forecastservice.domain.enums.Currency;
import ru.challenge.forecastservice.service.mapper.CsvDataToCurrencyDataMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

public class CsvDataLoader {
    private static final String CHARSET_NAME = "windows-1251";
    private static final List<String> paths = List.of(
            "csv_data/eur.csv",
            "csv_data/usd.csv",
            "csv_data/try.csv");

    private CsvDataLoader() {
    }

    public static Map<Currency, List<CurrencyData>> loadCurrencyData() {
        return paths.stream()
                .map(CsvDataLoader::read)
                .flatMap(List::stream)
                .map(CsvDataToCurrencyDataMapper::mapToCurrencyData)
                .collect(groupingBy(CurrencyData::currency));
    }

    private static List<String> read(String path) {
        try (BufferedReader reader = new BufferedReader(getInputStreamReader(path))) {
            final int headerLine = 1;
            return reader.lines().skip(headerLine).toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static InputStreamReader getInputStreamReader(String path) {
        return new InputStreamReader(
                Objects.requireNonNull(CsvDataLoader.class.getClassLoader().getResourceAsStream(path)),
                Charset.forName(CHARSET_NAME));
    }
}
