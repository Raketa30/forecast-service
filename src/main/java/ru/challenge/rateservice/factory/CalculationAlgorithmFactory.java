package ru.challenge.rateservice.factory;

import ru.challenge.rateservice.algorithm.CalculationAlgorithm;
import ru.challenge.rateservice.algorithm.impl.LastSevenDayAverageAlgorithm;
import ru.challenge.rateservice.domain.enums.AlgorithmType;

public class CalculationAlgorithmFactory {
    private CalculationAlgorithmFactory() {
    }

    public static CalculationAlgorithm create(AlgorithmType type) {
        return switch (type) {
            case LAST_SEVEN_DAYS_AVERAGE -> new LastSevenDayAverageAlgorithm();
        };
    }
}
