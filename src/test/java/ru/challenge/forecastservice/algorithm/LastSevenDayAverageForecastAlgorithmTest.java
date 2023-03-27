package ru.challenge.forecastservice.algorithm;

import org.junit.jupiter.api.Test;
import ru.challenge.forecastservice.algorithm.impl.LastSevenDayAverageForecastAlgorithm;
import ru.challenge.forecastservice.domain.CurrencyData;
import ru.challenge.forecastservice.domain.enums.Currency;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LastSevenDayAverageForecastAlgorithmTest {
    private final LastSevenDayAverageForecastAlgorithm algorithm = new LastSevenDayAverageForecastAlgorithm();

    @Test
    void shouldReturnZeroWhenDataEmpty() {
        double next = algorithm.forecastNext(List.of());
        assertEquals(0.0, next);
    }

    @Test
    void shouldReturnTenWhenUnsortedDataComing() {
        double next = algorithm.forecastNext(List.of(
                new CurrencyData(null, 300.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 1), 10.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 2), 10.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 3), 10.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 4), 10.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 5), 10.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 6), 10.0, Currency.EUR),
                new CurrencyData(LocalDate.of(2023, 1, 7), 10.0, Currency.EUR)
        ));
        assertEquals(10.0, next);
    }
}