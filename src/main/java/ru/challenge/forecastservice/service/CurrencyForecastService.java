package ru.challenge.forecastservice.service;

import ru.challenge.forecastservice.algorithm.ForecastAlgorithm;
import ru.challenge.forecastservice.domain.CurrencyData;
import ru.challenge.forecastservice.domain.ForecastData;
import ru.challenge.forecastservice.domain.enums.Currency;
import ru.challenge.forecastservice.domain.enums.Period;
import ru.challenge.forecastservice.service.mapper.CurrencyDataToRateMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrencyForecastService {

    private ForecastAlgorithm algorithm;

    public CurrencyForecastService(ForecastAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<ForecastData> forecast(List<CurrencyData> currencyData, Period period, Currency currency) {
        var forecastData = new ArrayList<ForecastData>();
        var currencyDataCopy = new ArrayList<>(currencyData);
        for (int day = 0; day < period.getDays(); day++) {
            var nextDayData = findNextDayCurrencyData(currency, currencyDataCopy, day);
            currencyDataCopy.add(nextDayData);
            forecastData.add(CurrencyDataToRateMapper.mapToForecastData(nextDayData));
        }
        return forecastData;
    }

    public void setAlgorithm(ForecastAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    private CurrencyData findNextDayCurrencyData(Currency currency, List<CurrencyData> currencyData, int days) {
        var nextDate = LocalDate.now().plusDays(days);
        var nextPrice = algorithm.forecastNext(currencyData);
        return new CurrencyData(nextDate, nextPrice, currency);
    }
}
