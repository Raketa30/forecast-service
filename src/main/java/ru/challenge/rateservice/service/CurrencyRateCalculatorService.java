package ru.challenge.rateservice.service;

import ru.challenge.rateservice.algorithm.CalculationAlgorithm;
import ru.challenge.rateservice.domain.CurrencyData;
import ru.challenge.rateservice.domain.CurrencyRate;
import ru.challenge.rateservice.domain.enums.Currency;
import ru.challenge.rateservice.domain.enums.Period;
import ru.challenge.rateservice.service.mapper.CurrencyDataToRateMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRateCalculatorService {

    private CalculationAlgorithm algorithm;

    public CurrencyRateCalculatorService(CalculationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<CurrencyRate> calculate(List<CurrencyData> currencyData, Period period, Currency currency) {
        var currencyRates = new ArrayList<CurrencyRate>();
        var currencyDataCopy = new ArrayList<>(currencyData);
        for (int day = 0; day < period.getDays(); day++) {
            var nextDayData = findNextDayCurrencyData(currency, currencyDataCopy, day);
            currencyDataCopy.add(nextDayData);
            currencyRates.add(CurrencyDataToRateMapper.mapToRate(nextDayData));
        }
        return currencyRates;
    }

    public void setAlgorithm(CalculationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    private CurrencyData findNextDayCurrencyData(Currency currency, List<CurrencyData> currencyData, int days) {
        var nextDate = LocalDate.now().plusDays(days);
        var nextPrice = algorithm.calculateNextPrice(currencyData);
        return new CurrencyData(nextDate, nextPrice, currency);
    }
}
