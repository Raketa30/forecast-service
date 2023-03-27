package ru.challenge.forecastservice.service;

import ru.challenge.forecastservice.domain.CurrencyData;
import ru.challenge.forecastservice.domain.enums.Currency;
import ru.challenge.forecastservice.repository.CurrencyDataInMemoryRepository;

import java.util.List;

public class CurrencyDataService {
    private final CurrencyDataInMemoryRepository currencyDataInMemoryRepository;

    public CurrencyDataService(CurrencyDataInMemoryRepository currencyDataInMemoryRepository) {
        this.currencyDataInMemoryRepository = currencyDataInMemoryRepository;
    }

    public List<CurrencyData> findByCurrency(Currency currency) {
        return currencyDataInMemoryRepository.findByCurrency(currency);
    }
}
