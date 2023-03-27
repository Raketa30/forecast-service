package ru.challenge.forecastservice.domain;

import ru.challenge.forecastservice.domain.enums.Currency;

import java.time.LocalDate;
import java.util.Objects;

public record CurrencyData(LocalDate date, double price, Currency currency) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyData that = (CurrencyData) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(date, that.date) && currency == that.currency;
    }

    @Override
    public String toString() {
        return "CurrencyData{" +
                "date=" + date +
                ", price=" + price +
                ", currency=" + currency +
                '}';
    }
}
