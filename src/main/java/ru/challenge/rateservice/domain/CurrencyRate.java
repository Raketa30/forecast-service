package ru.challenge.rateservice.domain;

import java.time.LocalDate;

public record CurrencyRate(LocalDate date, double price) {
}
