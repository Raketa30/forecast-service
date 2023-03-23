package ru.challenge.rateservice.algorithm;

import ru.challenge.rateservice.domain.CurrencyData;

import java.util.List;

public interface CalculationAlgorithm {
    double calculateNextPrice(List<CurrencyData> data);
}
