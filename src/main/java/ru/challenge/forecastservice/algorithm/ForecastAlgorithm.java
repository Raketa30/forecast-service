package ru.challenge.forecastservice.algorithm;

import ru.challenge.forecastservice.domain.CurrencyData;

import java.util.List;

public interface ForecastAlgorithm {
    double forecastNext(List<CurrencyData> data);
}
