package ru.challenge.forecastservice.service.mapper;

import ru.challenge.forecastservice.domain.CurrencyData;
import ru.challenge.forecastservice.domain.ForecastData;

public class CurrencyDataToRateMapper {
    private CurrencyDataToRateMapper() {
    }

    public static ForecastData mapToForecastData(CurrencyData currencyData) {
        return new ForecastData(currencyData.date(), currencyData.price());
    }
}
