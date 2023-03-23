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

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class CurrencyRateCalculatorService {

    private CalculationAlgorithm algorithm;

    public CurrencyRateCalculatorService(CalculationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<CurrencyRate> calculate(List<CurrencyData> currencyData, Period period, Currency currency) {
        var currencyRates = new ArrayList<CurrencyRate>();
        var currencyDataCopy = new ArrayList<>(currencyData);
        for (int i = 0; i < period.getDays(); i++) {
            var nextDayData = findNextDayCurrencyData(currency, currencyDataCopy, i);
            currencyDataCopy.add(nextDayData);
            currencyRates.add(CurrencyDataToRateMapper.mapToRate(nextDayData));
        }
        return currencyRates;
    }

    public void setAlgorithm(CalculationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    private CurrencyData findNextDayCurrencyData(Currency currency, List<CurrencyData> currencyData, int days) {
        currencyData.sort(comparing(CurrencyData::date, reverseOrder()));
        var nextDate = LocalDate.now().plusDays(days);
        var nextPrice = algorithm.calculateNextPrice(currencyData);
        return new CurrencyData(nextDate, nextPrice, currency);
    }
}
