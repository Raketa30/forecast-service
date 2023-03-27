package ru.challenge.forecastservice.algorithm.impl;

import ru.challenge.forecastservice.algorithm.ForecastAlgorithm;
import ru.challenge.forecastservice.domain.CurrencyData;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.*;

public class LastSevenDayAverageForecastAlgorithm implements ForecastAlgorithm {
    public static final int DAYS_FOR_CALCULATE = 7;

    @Override
    public double forecastNext(List<CurrencyData> data) {
        return Optional.ofNullable(data)
                .orElseGet(List::of)
                .stream()
                .sorted(comparing(CurrencyData::date,
                        nullsLast(reverseOrder())))
                .limit(DAYS_FOR_CALCULATE)
                .mapToDouble(CurrencyData::price)
                .average()
                .orElse(0.0);
    }
}
