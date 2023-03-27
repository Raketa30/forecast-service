package ru.challenge.forecastservice.factory;

import ru.challenge.forecastservice.algorithm.ForecastAlgorithm;
import ru.challenge.forecastservice.algorithm.impl.LastSevenDayAverageForecastAlgorithm;
import ru.challenge.forecastservice.domain.enums.AlgorithmType;

public class ForecastAlgorithmFactory {
    private ForecastAlgorithmFactory() {
    }

    public static ForecastAlgorithm create(AlgorithmType type) {
        return switch (type) {
            case LAST_SEVEN_DAYS_AVERAGE -> new LastSevenDayAverageForecastAlgorithm();
        };
    }
}
