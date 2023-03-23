package ru.challenge.rateservice.algorithm.impl;

import ru.challenge.rateservice.algorithm.CalculationAlgorithm;
import ru.challenge.rateservice.domain.CurrencyData;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.*;

public class LastSevenDayAverageAlgorithm implements CalculationAlgorithm {
    public static final int DAYS_FOR_CALCULATE = 7;

    @Override
    public double calculateNextPrice(List<CurrencyData> data) {
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
