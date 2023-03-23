package ru.challenge.rateservice.repository;

import ru.challenge.rateservice.domain.CurrencyData;
import ru.challenge.rateservice.domain.enums.Currency;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CurrencyDataInMemoryRepository {
    private final Map<Currency, List<CurrencyData>> currencyRateDataMap;

    public CurrencyDataInMemoryRepository(Map<Currency, List<CurrencyData>> currencyRateDataMap) {
        this.currencyRateDataMap = currencyRateDataMap;
    }

    public List<CurrencyData> findByCurrency(Currency currency) {
        return currencyRateDataMap.getOrDefault(currency, Collections.emptyList());
    }
}
