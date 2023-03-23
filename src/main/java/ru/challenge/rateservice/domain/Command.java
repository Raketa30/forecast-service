package ru.challenge.rateservice.domain;

import ru.challenge.rateservice.domain.enums.AlgorithmType;
import ru.challenge.rateservice.domain.enums.Currency;
import ru.challenge.rateservice.domain.enums.Period;

public record Command(Currency currency, Period period, AlgorithmType algorithmType) {

    @Override
    public String toString() {
        return "Command{" +
                "period=" + period +
                ", currency=" + currency +
                '}';
    }
}
