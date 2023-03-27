package ru.challenge.forecastservice.domain;

import ru.challenge.forecastservice.domain.enums.AlgorithmType;
import ru.challenge.forecastservice.domain.enums.Currency;
import ru.challenge.forecastservice.domain.enums.Period;

public record Command(Currency currency, Period period, AlgorithmType algorithmType) {

    @Override
    public String toString() {
        return "Command{" +
                "period=" + period +
                ", currency=" + currency +
                '}';
    }
}
