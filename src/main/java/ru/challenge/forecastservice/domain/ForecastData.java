package ru.challenge.forecastservice.domain;

import java.time.LocalDate;

public record ForecastData(LocalDate date, double price) {
}
