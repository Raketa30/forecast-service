package ru.challenge.rateservice.algorithm.impl;

import ru.challenge.rateservice.algorithm.CalculationAlgorithm;
import ru.challenge.rateservice.domain.CurrencyData;

import java.util.List;

public class LastSevenDayAverageAlgorithm implements CalculationAlgorithm {
    public static final int DAYS_FOR_CALCULATE = 7;

    @Override
    public double calculateNextPrice(List<CurrencyData> data) {
        return data.stream()
                .limit(DAYS_FOR_CALCULATE)
                .mapToDouble(CurrencyData::price)
                .average()
                .orElse(0.0);
    }
}
